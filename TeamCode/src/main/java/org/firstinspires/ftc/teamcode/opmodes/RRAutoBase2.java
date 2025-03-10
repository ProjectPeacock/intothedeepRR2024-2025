package org.firstinspires.ftc.teamcode.opmodes;

/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.SECONDS;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.CSAutoParams;
import org.firstinspires.ftc.teamcode.hardware.HWProfile;
import org.firstinspires.ftc.teamcode.libraries.RRMechOps;

//@Disabled
@Autonomous(name = "Auto Samples - EXPERIMENTAL", group = "Competition", preselectTeleOp = "GoBildaRi3D2425")
public class RRAutoBase2 extends LinearOpMode{

    public static String TEAM_NAME = "Project Peacock";
    public static int TEAM_NUMBER = 10355;

    //Define and declare Robot Starting Locations
    public enum START_POSITION {
        BLUE_SAMPLES,
        BLUE_SPECIMENS,
        RED_SAMPLES,
        RED_SPECIMENS
    }

    public static START_POSITION startPosition;

    public final static HWProfile robot = new HWProfile();
    public LinearOpMode opMode = this;
    public CSAutoParams params = new CSAutoParams();
    public RRMechOps mechOps = new RRMechOps(robot, opMode, params);

    @Override
    public void runOpMode() throws InterruptedException {

        //TODO: Initialize hardware
        robot.init(hardwareMap, false);

        //Key Pay inputs to selecting Starting Position of robot
        selectStartingPosition();


        while (!isStopRequested() && !opModeIsActive()) {
            // Wait for the DS start button to be touched.
            telemetry.addData("Selected Starting Position", startPosition);
            telemetry.addData(">", "Touch Play to start OpMode");
            telemetry.update();
        }

        //Game Play Button  is pressed
        if (opModeIsActive() && !isStopRequested()) {
            runAutonoumousMode();
        }
    }

    //end runOpMode();

    public void runAutonoumousMode() {
        //Initialize Pose2d as desired
        Pose2d initPose = new Pose2d(0, 0, 0); // Starting Pose
        Pose2d specimenScoringPosition = new Pose2d(0, 0, 0);
        Pose2d sampleScoringPosition = new Pose2d(0, 0, 0);
        Pose2d coloredSample1Position = new Pose2d(0, 0, 0);
        Pose2d coloredSample2Position = new Pose2d(0, 0, 0);
        Pose2d coloredSample3Position = new Pose2d(0, 0, 0);
        Pose2d grabSpecimenPosition = new Pose2d(0, 0, 0);
        Pose2d yellowSample1Position = new Pose2d(0, 0, 0);
        Pose2d yellowSample2Position = new Pose2d(0, 0, 0);
        Pose2d yellowSample3Position = new Pose2d(0, 0, 0);
        Pose2d midwayPose1 = new Pose2d(0, 0, 0);
        Pose2d midwayPose2 = new Pose2d(0, 0, 0);
        Pose2d midwayPose3 = new Pose2d(0, 0, 0);

        Pose2d parkPrepPose = new Pose2d(0, 0, 0);
        Pose2d parkPose = new Pose2d(0, 0, 0);
        double waitSecondsBeforeDrop = 0;
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);

//        switch (startPosition) {
//            case BLUE_SAMPLES:
//                drive = new MecanumDrive(hardwareMap, initPose);
//                sampleScoringPosition = new Pose2d(6, -20, Math.toRadians(-135));
//                yellowSample1Position = new Pose2d(30, 0, Math.toRadians(0));
//                yellowSample2Position = new Pose2d(30, -5, Math.toRadians(0));
//                yellowSample3Position = new Pose2d(30, -10, Math.toRadians(0));
//                midwayPose1 = new Pose2d(10,13,-90);
//                parkPose = new Pose2d(40, 0, 90);
//                break;
//
//            case RED_SAMPLES:
//                break;
//
//            case RED_SPECIMENS:
//                drive = new MecanumDrive(hardwareMap, initPose);
//                specimenScoringPosition = new Pose2d(30, 0, Math.toRadians(0));
//                grabSpecimenPosition = new Pose2d(2, -55, Math.toRadians(-90));
//                coloredSample1Position = new Pose2d(50, -40, Math.toRadians(0));
//                coloredSample2Position = new Pose2d(50, -50, Math.toRadians(0));
//                coloredSample3Position = new Pose2d(50, -60, Math.toRadians(0));
//                midwayPose1 = new Pose2d(25, -35, Math.toRadians(-90));
//                midwayPose2 = new Pose2d(50, -35, Math.toRadians(0));
//                parkPose = new Pose2d(3, -60, Math.toRadians(0));
//
//                break;
//
//            case BLUE_SPECIMENS:
//                drive = new MecanumDrive(hardwareMap, initPose);
//                specimenScoringPosition = new Pose2d(15, 0, 0);
//                grabSpecimenPosition = new Pose2d(3, 30, Math.toRadians(90));
//                coloredSample1Position = new Pose2d(0, 0, 0);
//                coloredSample2Position = new Pose2d(0, 0, 0);
//                coloredSample3Position = new Pose2d(0, 0, 0);
//                midwayPose1 = new Pose2d(10, 20, -90);
//                midwayPose2 = new Pose2d(30, 30, 90);
//                parkPose = new Pose2d(3, 15, 0);
//
//                break;
//        }


        drive = new MecanumDrive(hardwareMap, initPose);
        sampleScoringPosition = new Pose2d(4, 18, Math.toRadians(135));
        yellowSample1Position = new Pose2d(31, 7, Math.toRadians(0));
        yellowSample2Position = new Pose2d(31, 17, Math.toRadians(0));
        yellowSample3Position = new Pose2d(37.5, 20.5, Math.toRadians(90));
        midwayPose1 = new Pose2d(10,13, Math.toRadians(90));
        midwayPose2 = new Pose2d(10,0, Math.toRadians(0));
        midwayPose3 = new Pose2d(30,0, Math.toRadians(90));
        parkPrepPose = new Pose2d(10, -90, Math.toRadians(-90));
        parkPose = new Pose2d(0, 0, Math.toRadians(-45));

        /**
         * For Sample Scoring into high basket
         **/
        if (startPosition == START_POSITION.BLUE_SAMPLES ||
                startPosition == START_POSITION.RED_SAMPLES) {

            // Drive to scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(sampleScoringPosition.position, sampleScoringPosition.heading)
                            .build());


            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());

            // Drive to pick up Sample1 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(yellowSample1Position.position, yellowSample1Position.heading)
                            .build());
            // Pick up Sample1
            if(opModeIsActive()) {

            }

            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {

            }


            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());


            // Drive to scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(sampleScoringPosition.position, sampleScoringPosition.heading)
                            .build());


            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());


             //Drive to pickup Sample2 Position

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(yellowSample2Position.position, yellowSample2Position.heading)
                            .build());


            // Pick up Sample2
            if(opModeIsActive()) {

            }

            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {

            }


            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());


            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(sampleScoringPosition.position, sampleScoringPosition.heading)
                            .build());

            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());

            // Drive to Sample3 Position

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose3.position, midwayPose3.heading)
                            .build());

            if(opModeIsActive()) {

            }

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(yellowSample3Position.position, yellowSample3Position.heading)
                            .build());


            // Pick up Sample3
            if(opModeIsActive()) {

            }

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose3.position, midwayPose3.heading)
                            .build());


            // Raise Arm to high basket scoring position
            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {

            }

            // Drive to scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                          .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(sampleScoringPosition.position, sampleScoringPosition.heading)
                            .build());

            // Drive to prep position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .build());

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose2.position, midwayPose2.heading)
                            .strafeToLinearHeading(parkPose.position, parkPose.heading)
                            .build());

        }

            if(opModeIsActive()){
                mechOps.poleTouch();
            }

        //end of if (startPosition == BLUE_SAMPLES || RED_SAMPLES)

        /**
         *  For Specimen Scoring onto high bar
         **/
        if (startPosition == START_POSITION.BLUE_SPECIMENS ||
                startPosition == START_POSITION.RED_SPECIMENS) {

            // Raise Arm to high basket scoring position
            if(opModeIsActive()){
                // TODO: Add code to raise claw to high basket
            }

            // Drive to scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(specimenScoringPosition.position, specimenScoringPosition.heading)
                            .build());

            // Release the sample into the basket
            // Lower the arm
            if(opModeIsActive()) {
                // TODO: Add code to release the sample and lower the arm
            }

            // Drive to color sample1 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .strafeToLinearHeading(midwayPose2.position, midwayPose2.heading)
                            .strafeToLinearHeading(coloredSample1Position.position, coloredSample1Position.heading)
                            .build());

            // Push Color Sample1 into the Observation area
            // Drive to color sample1 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(grabSpecimenPosition.position, grabSpecimenPosition.heading)
                            .build());

            // Grab the specimen
            if(opModeIsActive()) {
                // TODO: Add code to grab the specimen from the observation area (from the floor)
            }

            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {
                // TODO: Add code to raise claw to specimen high bar
            }

            // Drive to specimen scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(specimenScoringPosition.position, specimenScoringPosition.heading)
                            .build());

            // Score the specimen on the high bar
            // Lower the arm
            if(opModeIsActive()) {
                // TODO: Add code to score the specimen
            }

            // Drive to Color Sample2 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .strafeToLinearHeading(midwayPose2.position, midwayPose2.heading)
                            .strafeToLinearHeading(coloredSample2Position.position, coloredSample2Position.heading)
                            .build());

            // Push Color Sample1 into the Observation area
            // Drive to color sample1 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(grabSpecimenPosition.position, grabSpecimenPosition.heading)
                            .build());

            // Grab the specimen
            if(opModeIsActive()) {
                // TODO: Add code to grab the specimen from the observation area (from the floor)
            }

            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {
                // TODO: Add code to raise claw to specimen high bar
            }

            // Drive to specimen scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(specimenScoringPosition.position, specimenScoringPosition.heading)
                            .build());

            // Score the specimen on the high bar
            // Lower the arm
            if(opModeIsActive()) {
                // TODO: Add code to score the specimen
            }

            // Drive to colored Sample3 Position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(midwayPose1.position, midwayPose1.heading)
                            .strafeToLinearHeading(midwayPose2.position, midwayPose2.heading)
                            .strafeToLinearHeading(coloredSample3Position.position, coloredSample3Position.heading)
                            .build());

            // Push Color Sample3 into the Observation area
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(grabSpecimenPosition.position, grabSpecimenPosition.heading)
                            .build());

            // Grab the specimen
            if(opModeIsActive()) {
                // TODO: Add code to grab the specimen from the observation area (from the floor)
            }

            // Raise Arm to high basket scoring position
            if(opModeIsActive()) {
                // TODO: Add code to raise claw to specimen high bar
            }

            // Drive to specimen scoring position
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(specimenScoringPosition.position, specimenScoringPosition.heading)
                            .build());

            // Score the specimen on the high bar
            // Lower the arm
            if(opModeIsActive()) {
                // TODO: Add code to score the specimen
            }

            // Park
            if(opModeIsActive()) {
                // TODO: Add code to park
                // set claw and motors into correct position
            }

            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(parkPose.position, parkPose.heading)
                            .build());

        }   //end of if (startPosition == BLUE_SPECIMENS || RED_SPECIMENS)

    }

    /**
     *
     */

    //Method to select starting position using X, Y, A, B buttons on gamepad
    public void selectStartingPosition() {
        State setupConfig = State.START_POSITION;
        Boolean menuActive = true;

        telemetry.setAutoClear(true);
        telemetry.clearAll();
        //******select start pose*****
        while(!isStopRequested() && menuActive){
            switch(setupConfig){
                case START_POSITION:
                    telemetry.addData("Initializing Autonomous:",
                            TEAM_NAME, " ", TEAM_NUMBER);
                    telemetry.addData("---------------------------------------","");
                    telemetry.addData("Select Starting Position using XYAB on Logitech (or ▢ΔOX on Playstayion) on gamepad 1:","");
                    telemetry.addData("    Blue Yellow Samples   ", "(X / ▢)");
                    telemetry.addData("    Blue Specimens ", "(Y / Δ)");
                    telemetry.addData("    Red Yellow Samples    ", "(B / O)");
                    telemetry.addData("    Red Specimens  ", "(A / X)");
                    if(gamepad1.x){
                        startPosition = START_POSITION.BLUE_SAMPLES;
                        menuActive = false;
                    }
                    if(gamepad1.dpad_left){
                        startPosition = START_POSITION.BLUE_SPECIMENS;
                        menuActive = false;
                    }
                    if(gamepad1.b){
                        startPosition = START_POSITION.RED_SAMPLES;
                        menuActive = false;
                    }
                    if(gamepad1.dpad_right){
                        startPosition = START_POSITION.RED_SPECIMENS;
                        menuActive = false;
                    }
                    telemetry.update();
                    break;
            }
            telemetry.update();
        }
        telemetry.clearAll();
    }

    //method to wait safely with stop button working if needed. Use this instead of sleep
    public void safeWaitSeconds(double time) {
        ElapsedTime timer = new ElapsedTime(SECONDS);
        timer.reset();
        while (!isStopRequested() && timer.time() < time) {
        }
    }

    public enum State {
        START_POSITION,
        PARK_POSITION
    }

}   // end class
