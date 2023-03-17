package Organization;

import Exceptions.WrongValuesException;

public class Coordinates {
        private float x;
        private int y;


        public Coordinates(float x, int y) throws WrongValuesException {
            this.x = x;
            this.y = y;
            if ((((Float) x instanceof Float)==false)||((Integer) y instanceof Integer)==false){
                throw new WrongValuesException("поле имеет неверное значение");
            }

        }
        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }


}
