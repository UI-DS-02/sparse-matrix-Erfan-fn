    public  class SinglyLinkedList {

        private Node head;
        private Node tail;
        private int size=0;
        public static class Node {

            private int row;
            private int column;
            private int element;
            private Node nextInRow;
            private Node nextInColumn;

            public Node(int element,int row,int column) {
                this.element = element;
                this.row=row;
                this.column=column;

            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public int getColumn() {
                return column;
            }

            public void setColumn(int column) {
                this.column = column;
            }

            public int getElement() {
                return element;
            }

            public void setElement(int element) {
                this.element = element;
            }

            public Node getNextInRow() {
                return nextInRow;
            }

            public void setNextInRow(Node nextInRow) {
                this.nextInRow = nextInRow;
            }

            public Node getNextInColumn() {
                return nextInColumn;
            }

            public void setNextInColumn(Node nextInColumn) {
                this.nextInColumn = nextInColumn;
            }

        }


        public int Size() {
            return size;
        }

        public Node first() {
            return this.head;
        }

        public Node last() {
            return this.tail;
        }

    }
