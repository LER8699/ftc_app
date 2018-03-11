package org.firstinspires.ftc.teamcode.mecanlift.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Enums;
import org.firstinspires.ftc.teamcode.mecanlift.controller.Mecanlift;

// Created on 2/3/2018 at 12:41 AM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode.mecanlift.autonomous

@Autonomous(name = "QUAL_RS_AUTO", group = "Iterative Opmode")
//@Disabled
public class RS_AUTO extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Mecanlift robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Mecanlift(this, Enums.Color.RED, Enums.Position.SIDE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (!isStarted()) { robot.doFullInitLoop(); }

        runtime.reset();

        robot.doFullAutonomous(this);

    }
}
