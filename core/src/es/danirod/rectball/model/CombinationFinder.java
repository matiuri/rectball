/*
 * This file is part of Rectball
 * Copyright (C) 2015 Dani Rodríguez
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombinationFinder {

    private final Board board;

    private final int width, height;

    private final List<Bounds> bounds;

    public CombinationFinder(Board board) {
        this.board = board;
        width = height = board.getSize();
        bounds = calculateCombinations();
    }

    private List<Bounds> calculateCombinations() {
        // FIXME: This method may return duplicated bounds.

        List<Bounds> possibleBounds = new ArrayList<>();
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width - 1; x++) {
                Coordinate coordinate = new Coordinate(x, y);
                BallColor reference = board.getBall(x, y).getColor();
                List<Coordinate> inRow = sameColorInRow(coordinate);
                List<Coordinate> inCol = sameColorInCol(coordinate);

                for (Coordinate rowCandidate : inRow) {
                    for (Coordinate colCandidate : inCol) {
                        int cx = rowCandidate.x;
                        int cy = colCandidate.y;
                        if (board.getBall(cx, cy).getColor() == reference) {
                            int bx = Math.min(cx, x);
                            int by = Math.min(cy, y);
                            int BX = Math.max(cx, x);
                            int BY = Math.max(cy, y);
                            possibleBounds.add(new Bounds(bx, by, BX, BY));
                        }
                    }
                }
            }
        }
        return possibleBounds;
    }

    private List<Coordinate> sameColorInRow(Coordinate ref) {
        List<Coordinate> candidates = new ArrayList<>();
        BallColor refColor = board.getBall(ref.x, ref.y).getColor();
        for (int i = ref.x + 1; i < width; i++) {
            if (board.getBall(i, ref.y).getColor() == refColor) {
                candidates.add(new Coordinate(i, ref.y));
            }
        }
        return candidates;
    }

    private List<Coordinate> sameColorInCol(Coordinate ref) {
        List<Coordinate> candidates = new ArrayList<>();
        BallColor refColor = board.getBall(ref.x, ref.y).getColor();
        for (int j = ref.y + 1; j < height; j++) {
            if (board.getBall(ref.x, j).getColor() == refColor) {
                candidates.add(new Coordinate(ref.x, j));
            }
        }
        return candidates;
    }

    public List<Bounds> getPossibleBounds() {
        return Collections.unmodifiableList(bounds);
    }

    public Bounds getCombination() {
        return bounds.isEmpty() ? null : bounds.get(0);
    }

    public boolean areThereCombinations() {
        return !(bounds.isEmpty());
    }
}
