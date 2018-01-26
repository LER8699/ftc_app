package org.firstinspires.ftc.teamcode.autonomous;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.autonomous.visuals.VisualsHandler;

// Created on 11/15/2017 at 7:47 PM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode

@Autonomous(name = "Jewel Test 2", group = "test")
//@Disabled
public class JewelTest2 extends LinearOpMode {

    private static final int BUTTON_ID = 3141592;


    private ElapsedTime runtime = new ElapsedTime();
    private VisualsHandler vh;

    @Override
    public void runOpMode() throws InterruptedException {

        vh = new VisualsHandler(this, true);

        Button button = new Button(hardwareMap.appContext);
        button.setText("Capture");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { try { vh.takeAndSavePic("jt2_pic.png"); } catch (InterruptedException e) {/*meh*/} }
        });
        button.setId(BUTTON_ID);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        vh.layout.createViews(button);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) ;
            // This is the time during which I can press the button and take pictures.
            //  Nothing needs to be done by the opmode.

        vh.close();

    }

}
