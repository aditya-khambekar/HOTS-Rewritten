// Copyright (c) 2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package org.team4639.lib.swerve;

public record ModuleLimits(
    double maxDriveVelocity, double maxDriveAcceleration, double maxSteeringVelocity) {}
