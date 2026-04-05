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

}