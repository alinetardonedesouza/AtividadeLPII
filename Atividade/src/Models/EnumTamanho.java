package Models;

// public enum EnumTamanho {

//     PP,
//     P,
//     M,
//     G,
//     GG,
//     XG,

// }

public enum EnumTamanho {

    PP {
       public void print(String tamanho) {
          System.out.println("PP");
       }
    },
    P {
       public void print(String tamanho) {
          System.out.println("P");
       }
    },
    M {
        public void print(String tamanho) {
           System.out.println("M");
        }
     },
     G {
        public void print(String tamanho) {
           System.out.println("G");
        }
     },
     GG {
        public void print(String tamanho) {
           System.out.println("GG");
        }
     },
     XG {
        public void print(String tamanho) {
           System.out.println("XG");
        }
     };
     
    abstract void print(String name);
 }
