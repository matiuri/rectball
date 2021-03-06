/*
 * This file is part of Rectball.
 * Copyright (C) 2015 Dani Rodríguez.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.danirod.rectball.model;

import com.badlogic.gdx.graphics.Color;

/**
 * Enumerated type for representing the colors balls can have. This enumared
 * type is also responsible for getting the texture region of a sheet for
 * representing that color on the screen.
 *
 * @author danirod
 */
public enum BallColor {
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    YELLOW(Color.YELLOW);

    private Color color;

    BallColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
