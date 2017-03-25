/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotHandlers.ToggleManager;

// Created on 3/6/2017 at 10:19 AM by Chandler, originally part of ftc_app under org.firstinspires.ftc.teamcode

@TeleOp(name = "AutoCheckBeaconTest", group = "Iterative Opmode")
// @Autonomous(...) is the other common choice
//@Disabled
public class AutoCheckBeaconTest extends OpMode {
    //Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private Vuforia2017Manager vuforia;

    private ToggleManager checkBeaconToggle;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void init() {

        vuforia = new Vuforia2017Manager(true);

        checkBeaconToggle = new ToggleManager() {
            @Override
            public void onToggle() {
                try {
                    vuforia.checkOnBeacons(1);
                } catch (Exception e) {
                    //meh
                }
            }
        };

        telemetry.addData("Status", "Initialized");

    }

    //Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {}

    //Code to run ONCE when the driver hits PLAY
    @Override
    public void start() {
        runtime.reset();
        vuforia.start();
    }

    //Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {

        checkBeaconToggle.doToggle(gamepad1.a);

        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addData("Beacon Config", Vuforia2017Manager.decodeBeaconConfig(vuforia.getBeaconConfig(1)));
    }

    //Code to run ONCE after the driver hits STOP
    @Override
    public void stop() {

        // eg: Set all motor powers to 0


    }

}
