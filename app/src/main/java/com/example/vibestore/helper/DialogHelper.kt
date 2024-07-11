package com.example.vibestore.helper

import android.content.Context
import android.graphics.Typeface
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.vibestore.R

object DialogHelper {

    private fun getCustomFont(context: Context): Typeface? {
        return ResourcesCompat.getFont(context, R.font.poppinsregular)
    }

    private fun showDialog(
        context: Context,
        title: String?,
        textContent: String?,
        alertType: Int,
        onConfirm: () -> Unit = {}
    ) : SweetAlertDialog {
        val customFont = getCustomFont(context)

        val dialog = SweetAlertDialog(context, alertType)
            .setTitleText(title)
            .setContentText(textContent)
            .setConfirmText("OK")
            .setConfirmClickListener {
                onConfirm()
                it.dismissWithAnimation()
            }

        dialog.setCanceledOnTouchOutside(true)

        dialog.show()

        val titleText = dialog.findViewById<TextView>(cn.pedant.SweetAlert.R.id.title_text)
        val contentText = dialog.findViewById<TextView>(cn.pedant.SweetAlert.R.id.content_text)
        val confirmButton = dialog.findViewById<Button>(cn.pedant.SweetAlert.R.id.confirm_button)


        titleText?.typeface = customFont
        contentText?.typeface = customFont
        confirmButton?.typeface = customFont

        confirmButton.backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)

        return dialog
    }

    fun showDialogLoading(
        context: Context,
        textContent: String?
    ): SweetAlertDialog {
        return showDialog(
            context = context,
            title = null,
            textContent = textContent,
            alertType = SweetAlertDialog.PROGRESS_TYPE
        )
    }

    fun showDialogSuccess(
        context: Context,
        title: String?,
        textContent: String?,
        onConfirm: () -> Unit = {}
    ): SweetAlertDialog {
        return showDialog(
            context = context,
            title = title,
            textContent = textContent,
            alertType = SweetAlertDialog.SUCCESS_TYPE,
            onConfirm = onConfirm
        )
    }
}