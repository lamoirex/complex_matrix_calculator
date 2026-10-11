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
