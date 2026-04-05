package com.example.myapplication.present.activity.order.history.tab

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.common.AppConst
import com.example.myapplication.common.UiState
import com.example.myapplication.common.base.BaseFragment
import com.example.myapplication.common.base.ext.showToastOnce
import com.example.myapplication.common.toListResOrderDTO
import com.example.myapplication.databinding.FragmentTabOrderBinding
import com.example.myapplication.domain.model.dto.res.ResOrderDTO
import com.example.myapplication.present.activity.order.history.OrderViewModel
import com.example.myapplication.present.dialog.CommentDialog
import com.example.myapplication.present.dialog.ConfirmCancelOrderDialog
import com.example.myapplication.present.dialog.ConfirmCompleteDialog
import com.example.myapplication.present.dialog.LoadingDialog
import com.example.myapplication.present.dialog.ReasonCancelDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TabOrderFragment : BaseFragment<FragmentTabOrderBinding>() {
    private val viewModel: OrderViewModel by activityViewModels()

    private var orderAdapter: OrderAdapter? = null
    private var loadingDialog: LoadingDialog? = null
    private var commentDialog: CommentDialog? = null
    private var confirmCancelOrderDialog: ConfirmCancelOrderDialog? = null
    private var confirmCompleteDialog: ConfirmCompleteDialog? = null
    private var reasonCancelDialog: ReasonCancelDialog? = null

    private var resOrder: ResOrderDTO? = null

    private var index: Int = 0

    override fun inflateBinding(): FragmentTabOrderBinding =
        FragmentTabOrderBinding.inflate(layoutInflater)
    //`feat: thêm TabOrderFragment hiển thị và xử lý thao tác đơn hàng theo tab`
    override fun initViews() {
        super.initViews()

        index = arguments?.getInt(AppConst.KEY_ORDER_TYPE) ?: 0

        loadingDialog = LoadingDialog(requireContext())

        commentDialog = CommentDialog(
            contextParams = requireContext(),
            onLater = {

            }, onRate = { stars, comment, orderId ->
                viewModel.postComment(
                    orderId = resOrder?._id ?: "",
                    productIds = resOrder?.products?.map { it.productId?._id ?: "" } ?: emptyList(),
                    stars = stars,
                    comment = comment
                )
            }
        )

        reasonCancelDialog = ReasonCancelDialog(
            contextParams = requireContext(),
            onMessage = { message ->
                if (message.isEmpty()) {
                    requireContext().showToastOnce(getString(R.string.msg_input_null))
                    return@ReasonCancelDialog
                }

                viewModel.cancelOrderUseCase(
                    resOrder?._id ?: "",
                    AppConst.STATUS_ORDER_TO_CANCELLED,
                    message
                )
            }
        )
        //`feat: bổ sung khởi tạo dialog đánh giá và hủy đơn trong TabOrderFragment`
        confirmCancelOrderDialog = ConfirmCancelOrderDialog(
            requireContext(),
            {

            }, {
                reasonCancelDialog?.show()
            }
        )

        confirmCompleteDialog = ConfirmCompleteDialog(
            requireContext(),
            {

            }, {
                viewModel.updateOrderUseCase(cacheId, AppConst.STATUS_ORDER_TO_COMPLETED)
                requireContext().showToastOnce(getString(R.string.msg_confirm_success))
            }
        )
        //`feat: bổ sung khởi tạo dialog xác nhận hủy và hoàn tất đơn hàng trong TabOrderFragment`
        binding.rcvOrder.apply {
            orderAdapter = OrderAdapter(
                contextParams = requireContext(),
                status = when (index) {
                    0 -> AppConst.STATUS_ORDER_TO_PAY
                    1 -> AppConst.STATUS_ORDER_TO_RECEIVE
                    2 -> AppConst.STATUS_ORDER_TO_COMPLETED
                    else -> AppConst.STATUS_ORDER_TO_CANCELLED
                },
                onClickItem = { index, res ->

                }, onCancel = { index, res ->
                    resOrder = res
                    confirmCancelOrderDialog?.show()
                }, onReview = { index, res ->
                    resOrder = res
                    commentDialog?.showDialog(res._id ?: "")
                }, onConfirm = { index, res ->
                    cacheId = res._id ?: ""
                    confirmCompleteDialog?.show()
                }
            )

            adapter = orderAdapter
        }
    }
    //`feat: bổ sung khởi tạo OrderAdapter và xử lý thao tác đơn hàng trong TabOrderFragment`
    private var cacheId: String = ""

    override fun observeData() {
        super.observeData()

        lifecycleScope.launch {
            viewModel.listOrder.collect { listOrder ->
                val data = listOrder.filter {
                    when (index) {
                        0 -> it.status == AppConst.STATUS_ORDER_TO_PAY
                        1 -> it.status == AppConst.STATUS_ORDER_TO_RECEIVE
                        2 -> it.status == AppConst.STATUS_ORDER_TO_COMPLETED
                        else -> it.status == AppConst.STATUS_ORDER_TO_CANCELLED
                    }
                }
                orderAdapter?.comments = viewModel.state.value.listCommentCaches.toMutableList()
                orderAdapter?.submitData(data.toListResOrderDTO())
            }
        }

        lifecycleScope.launch {
            viewModel.uiStateUpdate.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> {
                        loadingDialog?.cancel()
                        requireContext().showToastOnce(getString(R.string.msg_wrong))
                        viewModel.changeStateUpdateToIdle()
                    }

                    UiState.Idle -> {}
                    UiState.Loading -> {
                        loadingDialog?.cancel()
                    }

                    is UiState.Success -> {
                        loadingDialog?.cancel()

                        val response = uiState.data
                        val listOrderTemp = viewModel.listOrder.first().toListResOrderDTO()
                        val indexResponse = listOrderTemp.indexOfFirst { it._id == response._id }

                        if (indexResponse != -1) {
                            val newList = listOrderTemp.toMutableList()
                            newList[indexResponse] = newList[indexResponse].copy(
                                status = response.status
                            )
                            viewModel.changeStateUpdateToIdle()

                            return@collect
                        }

//                        requireContext().showToastOnce(getString(R.string.msg_wrong))
                        viewModel.changeStateUpdateToIdle()
                    }
                }
            }
        }
        //`feat: bổ sung quan sát dữ liệu đơn hàng và cập nhật trạng thái trong TabOrderFragment`
        lifecycleScope.launch {
            viewModel.stateComment.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> {
                        loadingDialog?.cancel()
                        commentDialog?.cancel()

                        viewModel.resetStateComment()
                    }

                    UiState.Idle -> {
                        commentDialog?.cancel()
                        loadingDialog?.cancel()
                    }

                    UiState.Loading -> {
                        commentDialog?.cancel()
                        loadingDialog?.show()
                    }

                    is UiState.Success<*> -> {
                        loadingDialog?.cancel()
                        commentDialog?.cancel()
                        requireContext().showToastOnce(getString(R.string.thanks_for_rating))

                        viewModel.resetStateComment()
                    }
                }
            }
        }
        //`feat: bổ sung xử lý trạng thái đánh giá đơn hàng trong TabOrderFragment`
    }

}