package org.firstinspires.ftc.teamcode.mecanlift.teleop.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mecanlift.controller.Mecanlift;

// Created on 2/23/2018 at 11:47 AM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode.mecanlift.teleop.test

@TeleOp(name = "Drive Test", group = "test")
//@Disabled
public class DriveTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Mecanlift robot;

    @Override
    public void init() {
        robot = new Mecanlift(this);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        robot.runDrive();
        telemetry.addData("Status", "Running: " + runtime.toString());
    }

    @Override
    public void stop() { robot.stop(); }

}