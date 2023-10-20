package com.niraj.motionlayout_part1

import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility

val normalState = ConstraintSet {
    val sourceName = createRefFor("sourceName")
    val title = createRefFor("title")
    val body = createRefFor("body")
    val imageView = createRefFor("imageView")
    val cardView = createRefFor("cardView")

//    constrain(cardView) {
//        width = Dimension.value(350.dp)
//        height = Dimension.value(90.dp)
//    }

    constrain(imageView) {
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        top.linkTo(parent.top, 15.dp)
        bottom.linkTo(parent.bottom, 15.dp)
        width = Dimension.value(65.dp)
        height = Dimension.value(65.dp)
    }


    constrain(sourceName) {
        absoluteLeft.linkTo(imageView.absoluteRight, 10.dp)
        top.linkTo(parent.top, 15.dp)
    }

    constrain(title) {
        absoluteLeft.linkTo(imageView.absoluteRight, 10.dp)
        end.linkTo(parent.absoluteRight)
        top.linkTo(sourceName.bottom, 10.dp)
        width = Dimension.fillToConstraints
    }

    constrain(body) {
        top.linkTo(title.bottom)
        absoluteRight.linkTo(parent.absoluteRight, 15.dp)
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        visibility = Visibility.Gone
    }
}

val expandedState = ConstraintSet {
    val sourceName = createRefFor("sourceName")
    val title = createRefFor("title")
    val body = createRefFor("body")
    val imageView = createRefFor("imageView")
    val cardView = createRefFor("cardView")

//    constrain(cardView) {
//        width = Dimension.value(350.dp)
//        height = Dimension.value(500.dp)
//    }

    constrain(imageView) {
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        absoluteRight.linkTo(parent.absoluteRight, 15.dp)
        top.linkTo(parent.top, 15.dp)
        width = Dimension.fillToConstraints
        height = Dimension.value(224.dp)
    }

    constrain(sourceName) {
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        top.linkTo(imageView.bottom, 15.dp)
    }

    constrain(title) {
        top.linkTo(sourceName.bottom, 10.dp)
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        width = Dimension.fillToConstraints
    }

    constrain(body) {
        top.linkTo(title.bottom, 10.dp)
        absoluteLeft.linkTo(parent.absoluteLeft, 15.dp)
        end.linkTo(parent.absoluteRight, 15.dp)
        width = Dimension.fillToConstraints
        visibility = Visibility.Visible
    }
}
