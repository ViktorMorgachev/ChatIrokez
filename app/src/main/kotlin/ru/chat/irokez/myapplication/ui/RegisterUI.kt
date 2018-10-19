package ru.chat.irokez.myapplication.ui

import android.support.constraint.ConstraintSet.BOTTOM
import android.support.constraint.ConstraintSet.LEFT
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import ru.chat.irokez.myapplication.R

class RegisterUI : AnkoComponent<AppCompatActivity> {


    override fun createView(ui: AnkoContext<AppCompatActivity>): View = with(ui) {
        constraintLayout {
            backgroundResource = R.color.colorPrimary
            lparams(matchParent, matchParent)

            val chatImage = imageView(R.drawable.chat_500x500) {
                id = R.id.image
            }.lparams(matchParent, dip(0)) {
            }


            val nickNameField = editText {
                id = R.id.et_nick
            }.lparams(dip(0), wrapContent)

            val ok = button {
                id = R.id.btn_register
                backgroundResource = R.color.button_color
            }.lparams(wrapContent, wrapContent)

            // Заглушки для построения макета

            view {
                id = R.id.lower_limiter
            }.lparams(dip(0), dip(0))

            view {
                id = R.id.right_limiter
            }.lparams(dip(0), dip(0))

            view {
                id = R.id.left_limiter
            }.lparams(dip(0), dip(0))


            applyConstraintSet {
                chatImage{
                    connect(
                    )
                }
            }
        }
    }

}
