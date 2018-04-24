package org.firstinspires.ftc.teamcode.mecanlift.controller;

// Created on 1/26/2018 at 3:56 PM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Lift {

    public static final int
            thres = 10;     // Threshold for encoders
    private static int
            lift0 = 0;     // Ground level
    private static final int
            lift1 = 1750,   // 1 glyph (6in) high
            lift2 = 3520;   // 2 glyphs (1ft) high
    private static final double
            liftS   = 1,    // Speed lift moves at when going to positions
            liftDS  = .25;  // Speed lift moves at when directly controlled

    private DcMotor l;
    private boolean
            upd = false,
            gd = false,
            ddupd = false,
            dddownd = false, 
            fixd = false,
            eGood = false;

    public Lift(DcMotor lift) {
        l = lift;
        l.setDirection(DcMotorSimple.Direction.REVERSE);
        l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void start () {
        if (lift0 == 0) {eGood = true; return;}
        l.setTargetPosition(lift0);
        l.setPower(liftS);
        while (!update_encoders(l));
        eGood = true;
    }

    // TODO: Add grounding code (and method)
    @Deprecated public void run (boolean up, boolean ground, boolean dd_up, boolean dd_down) {

        do_up(up);

        do_ground(ground);

        update_encoders();

        do_ddup(dd_up);

        do_dddown(dd_down);

    }
    public void run (boolean up, boolean ground, boolean dd_up, boolean dd_down, boolean fix) {

        do_up(up);

        do_ground(ground);

        update_encoders();

        do_ddup(dd_up);

        do_dddown(dd_down);
        
        do_fix(fix);

    }

    public void stop() { l.setPower(0); }

    static boolean update_encoders (DcMotor m) {
        if (m.getMode() != DcMotor.RunMode.RUN_TO_POSITION) return false;
        boolean ret = m.getCurrentPosition() < m.getTargetPosition() + thres &&
                m.getCurrentPosition() > m.getTargetPosition() - thres;
        if (ret) m.setPower(0);
        return ret;
    }

    private void update_encoders () {
        if (eGood) return;
        eGood = update_encoders(l);
    }

    private void do_up (boolean b) {
        if (upd && !b) { upd = false; return; }
        if (!b) return;
        if (!upd) {
            if ((l.getCurrentPosition() >= lift1 - thres && l.getCurrentPosition() < lift2 - thres) || l.getTargetPosition() == lift1) lift2();
            else lift();
            upd = true;
        }
    }
    private void do_ground (boolean b) {
        if (gd && !b) { gd = false; return; }
        if (!b) return;
        if (!gd) { l.setTargetPosition(lift0); l.setPower(liftS); eGood = false; gd = true; }
    }
    private void do_ddup (boolean b) {
        if (ddupd && !b) {
            l.setPower(0);
            ddupd = false;
            l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return;
        }
        if (!b) return;
        if (!ddupd) {
            l.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            l.setPower(liftDS);
            ddupd = true;
        }
    }
    private void do_dddown (boolean b) {
        if (dddownd && !b) {
            l.setPower(0);
            dddownd = false;
            l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return;
        }
        if (!b) return;
        if (!dddownd) {
            l.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            l.setPower(-liftDS);
            dddownd = true;
        }
    }
    private void do_fix (boolean b) {
        if (fixd && !b) { fixd = false; return; }
        if (!b) return;
        if (!fixd) {
            l.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            l.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            l.setTargetPosition(lift0);
            l.setPower(liftS);
            eGood = false; 
            fixd = true; 
        }
    }

    void grabbedBlock () {
        lift0 += 100; if (grounded()) ground();
    }
    void releasedBlocks () {
        lift0 = 0; if (grounded()) ground();
    }

    /** FOR AUTONOMOUS USE */
    public void setPosition (int pos) { l.setTargetPosition(pos); l.setPower(liftS); eGood = false; }
    public void addToPosition (int add) { setPosition(l.getCurrentPosition() + add); }
    void ground () { l.setTargetPosition(lift0); l.setPower(liftS); eGood = false; }
    void groundground () { l.setTargetPosition(0); l.setPower(.5); eGood = false; }
    void lift () { l.setTargetPosition(lift1); l.setPower(liftS); eGood = false; }
    void lift2 () { l.setTargetPosition(lift2); l.setPower(liftS); eGood = false; }
    boolean grounded () { return l.getCurrentPosition() < Rotater.flip_position; }
    boolean grounding () { return l.getTargetPosition() == lift0; }
    boolean goingUp () { return l.getTargetPosition() >= lift1; }
    @Deprecated public void waitForEncoders () { while (!update_encoders(l)); }
    void waitForEncoders (LinearOpMode opmode) { while (!update_encoders(l) && opmode.opModeIsActive()); }

}
