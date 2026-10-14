public class ComplexMatrix {
    private final int rows;
    private final int cols;
    private final Complex[][] data;

    public ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными");
        }
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Complex get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, Complex value) {
        data[i][j] = value;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Сложить нельзя(у матриц разные размеры)");
        }
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].plus(other.data[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Умножить нельзя: число столбцов в первой матрице должно быть равно числу строк во второй");
        }
        ComplexMatrix result = new ComplexMatrix(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < cols; k++) {
                    sum = sum.plus(data[i][k].multiply(other.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = data[i][j];
            }
        }
        return result;
    }

    private ComplexMatrix copy() {
        ComplexMatrix result = new ComplexMatrix(rows, cols);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j];
            }
        }
        return result;
    }

    private void swapRows(int row1, int row2) {
        Complex[] temp = data[row1];
        data[row1] = data[row2];
        data[row2] = temp;
    }

    private int findPivotRow(int col, int startRow) {
        for (int i = startRow; i < rows; i++) {
            if (!data[i][col].isZero()) {
                return i;
            }
        }
        return -1;
    }

    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Определитель есть только у квадратной матрицы!");
        }
        ComplexMatrix a = copy();
        Complex det = new Complex(1,0);

        for (int col = 0; col < rows; col++) {
            int pivotRow = a.findPivotRow(col, col);
            if (pivotRow == -1) {
                return new Complex(0, 0);
            }
            if (pivotRow != col) {
                a.swapRows(pivotRow, col);
                det = det.multiply(new Complex(-1, 0));
            }
            Complex pivot = a.data[col][col];
            det = det.multiply(pivot);

            for (int i = col + 1; i < rows; i++) {
                Complex factor = a.data[i][col].divide(pivot);
                for (int j = 0; j < cols; j++) {
                    Complex minus = factor.multiply(a.data[col][j]);
                    a.data[i][j] = a.data[i][j].minus(minus);
                }
            }
        }
        return det;
    }

    public ComplexMatrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException("Обратная матрица есть только у квадратной матрицы!");
        }
        ComplexMatrix a = copy();
        ComplexMatrix e = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            e.data[i][i] = new Complex(1, 0);
        }

        for (int col = 0; col < rows; col++) {
            int pivotRow = a.findPivotRow(col, col);
            if (pivotRow == -1) {
                throw new ArithmeticException("Матрица вырожденная, поэтому обратной нет");
            }
            a.swapRows(pivotRow, col);
            e.swapRows(pivotRow, col);

            Complex pivot = a.data[col][col];
            for (int j = 0; j < cols; j++) {
                a.data[col][j] = a.data[col][j].divide(pivot);
                e.data[col][j] = e.data[col][j].divide(pivot);
            }

            for (int i = 0; i < rows; i++) {
                if (i != col) {
                    Complex factor = a.data[i][col];
                    for (int j = 0; j < cols; j++) {
                        a.data[i][j] = a.data[i][j].minus(factor.multiply(a.data[col][j]));
                        e.data[i][j] = e.data[i][j].minus(factor.multiply(e.data[col][j]));
                    }
                }
            }
        }
        return e;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        ComplexMatrix otherInverse = other.inverse();
        return multiply(otherInverse);
    }

    @Override 
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ComplexMatrix)) return false;
        ComplexMatrix other = (ComplexMatrix) o;
        if (rows != other.rows || cols != other.cols) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!data[i][j].equals(other.data[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                text += data[i][j] + " ";
            }
            text += "\n";
        }
        return text;
    }
}
