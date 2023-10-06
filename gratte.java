import java.util.*;

public class gratte {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez un entier...");
        int n = sc.nextInt();
        System.out.println();// pour contenir la taille de la grille
        int[][] tableau = creer(n);// pour contenir la table de nombre ne se répétant pas
        // afficheGrille(tableau, tableau);
        int[][] tab = new int[n][n];
        afficheGrille(tab, tableau);
        while (true) {
            System.out.println("Entrez 0 pour finir ");
            System.out.print("\nEntrez la ligne ");
            int ligne = sc.nextInt();
            if (ligne == 0)
                break;
            System.out.print("\nEntrez la colonne ");
            int col = sc.nextInt();
            if (col == 0)
                break;
            System.out.print("\nEntrez le nombre ");
            int nombre = sc.nextInt();
            System.out.println();
            if (nombre == 0)
                break;
            try {
                tab[ligne - 1][col - 1] = nombre;
                afficheGrille(tab, tableau);
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Entree invalide");
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tab[i][j] == tableau[i][j])
                        count++;
                }
            }
            if (count == n * n) {
                System.out.println("Vous avez gagné");
                break;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i][j] == tableau[i][j])
                    count++;
            }
        }
        if (count == n * n) {
            System.out.println("Vous avez gagné");
        } else {
            System.out.println("Vous avez perdu");
        }
        sc.close();

    }

    // public static void jouer(int[][] tableau) {
    // Scanner sc = new Scanner(System.in);
    // int n = tableau[0].length;
    // sc.close();
    // }

    public static int[][] creer(int n) {
        // ici je vais essayer de créer la table de nombre ne se répétant
        // Ici c'est le remplissage de la première ligne
        int[][] tableau = new int[n][n];
        tableau[0][0] = (int) (Math.random() * n + 1);
        tableau[0][1] = (int) (Math.random() * n + 1);
        while (tableau[0][1] == tableau[0][0]) {
            tableau[0][1] = (int) (Math.random() * n + 1);
        }
        int m = 0;
        for (int i = 2; i < n; i++) {
            int[] table = new int[i];
            for (int j = 0; j < i; j++) {
                table[j] = tableau[0][j];
            }
            int affiche = 0;
            while (true) {
                m = (int) (Math.random() * n + 1);
                for (int j = 0; j < i; j++) {
                    affiche = 0;
                    if (m == table[j])
                        break;
                    affiche = 1;
                }
                if (affiche == 1)
                    break;
            }
            tableau[0][i] = m;
        }
        // for (int j = 0; j < tableau.length; j++) {
        // System.out.print(tableau[0][j] + " ");
        // }
        // System.out.println();
        // J'ai fini avec la première ligne
        // // Place à la deuxième ligne
        for (int i = 0; i < n; i++) {
            if (tableau[0][0] != (i + 1)) {
                tableau[1][0] = (i + 1);
                break;
            }
        }
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < n; j++) {
                try {
                    int[] tab1 = new int[j];
                    int[] tab2 = new int[i];
                    int[] tab3 = new int[n];
                    int x = 0;
                    for (int w = 0; w < i; w++)
                        tab2[w] = tableau[w][j];
                    for (int w = 0; w < j; w++)
                        tab1[w] = tableau[i][w];
                    for (int k = 1; k <= n; k++) {
                        if (j == 0) {
                            if (!existe(tab2, k))
                                tab3[x++] = k;
                        } else {
                            if (!existe(tab1, k) && !existe(tab2, k))
                                tab3[x++] = k;
                        }
                    }
                    tableau[i][j] = tab3[0];
                    if (tableau[i][j] == 0)
                        tableau = corrige(tableau, i, j);
                    // System.out.println("Debut tab1");
                    // for (int z = 0; z < tab1.length; z++)
                    // System.out.print(tab1[z] + " ");
                    // System.out.println("Fin tab1");
                    // System.out.println("Debut tab2");
                    // for (int z = 0; z < tab2.length; z++)
                    // System.out.print(tab2[z] + " ");
                    // System.out.println("Fin tab2");
                    // System.out.println("Debut tab 3");
                    // for (int z = 0; z < tab3.length; z++)
                    // System.out.print(tab3[z] + " ");
                    // System.out.println("fIN tab3");
                } catch (ArrayIndexOutOfBoundsException err) {
                    // System.out.println(err.${getMessage()});
                }
            }
        }
        return tableau;
    }

    public static void affiche(int[][] tableau) {
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                System.out.print(tableau[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean existe(int[] tab, int x) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == x)
                return true;
        }
        return false;
    }

    public static boolean possible1(int[][] tableau, int i, int j) {
        int[] tab1 = new int[j + 1];
        int[] tab2 = new int[i];
        int[] tab3 = new int[tableau.length];
        int x = 0;
        for (int w = 0; w < i; w++)
            tab2[w] = tableau[w][j];
        for (int w = 0; w < j; w++)
            tab1[w] = tableau[i][w];
        tab1[j] = tableau[i][j];
        for (int k = 1; k <= tableau.length; k++) {
            if (!existe(tab1, k) && !existe(tab2, k) && k > tab1[j])
                tab3[x++] = k;
        }
        return tab3[0] != 0;
    }

    public static int[][] possible2(int[][] tableau, int i, int j) {
        int[] tab1 = new int[j + 1];
        int[] tab2 = new int[i];
        int[] tab3 = new int[tableau.length];
        int x = 0;
        for (int w = 0; w < i; w++)
            tab2[w] = tableau[w][j];
        for (int w = 0; w < j; w++)
            tab1[w] = tableau[i][w];
        tab1[j] = tableau[i][j];
        for (int k = 1; k <= tableau.length; k++) {
            if (!existe(tab1, k) && !existe(tab2, k) && k > tab1[j])
                tab3[x++] = k;
        }
        tableau[i][j] = tab3[0];
        return tableau;
    }

    public static int[][] corrige(int[][] tableau, int ligne, int col) {
        // System.out.println(possible1(tableau, ligne, col - 1));
        // while(tableau[ligne][col] == 0){

        if (possible1(tableau, ligne, col - 1)) {
            tableau = possible2(tableau, ligne, col - 1);
            tableau[ligne][col] = 0;
            if (possible1(tableau, ligne, col)) {
                tableau = possible2(tableau, ligne, col);
            } else {
                tableau = corrige(tableau, ligne, col);
            }
        } else {
            if (possible1(tableau, ligne, col - 2)) {
                tableau = possible2(tableau, ligne, col - 2);
                tableau[ligne][col - 1] = 0;
                if (possible1(tableau, ligne, col - 1)) {
                    tableau = possible2(tableau, ligne, col - 1);
                    tableau[ligne][col] = 0;
                    if (possible1(tableau, ligne, col)) {
                        tableau = possible2(tableau, ligne, col);
                    } else {
                        if (possible1(tableau, ligne, col - 1)) {
                            tableau = possible2(tableau, ligne, col - 1);
                            tableau[ligne][col] = 0;
                            tableau = possible2(tableau, ligne, col);
                            if (tableau[ligne][col] == 0)
                                tableau = corrige(tableau, ligne, col);
                        } else {
                            if (possible1(tableau, ligne, col - 2)) {
                                tableau = possible2(tableau, ligne, col - 2);
                                tableau[ligne][col - 1] = 0;
                                if (possible1(tableau, ligne, col - 1)) {
                                    tableau = possible2(tableau, ligne, col - 1);
                                    tableau[ligne][col] = 0;
                                    tableau = possible2(tableau, ligne, col);
                                    if (tableau[ligne][col] == 0)
                                        tableau = corrige(tableau, ligne, col);
                                } else {
                                    tableau = corrige(tableau, ligne, col - 1);
                                    tableau[ligne][col] = 0;
                                    tableau = possible2(tableau, ligne, col);
                                    if (tableau[ligne][col] == 0)
                                        tableau = corrige(tableau, ligne, col);
                                }
                            } else {
                                tableau = corrige(tableau, ligne, col - 2);
                                tableau[ligne][col - 1] = 0;
                                tableau = possible2(tableau, ligne, col - 1);
                                if (tableau[ligne][col - 1] == 0) {
                                    tableau = corrige(tableau, ligne, col - 1);
                                    tableau[ligne][col] = 0;
                                    tableau = possible2(tableau, ligne, col);
                                    if (tableau[ligne][col] == 0)
                                        tableau = corrige(tableau, ligne, col);
                                } else {
                                    tableau[ligne][col] = 0;
                                    if (possible1(tableau, ligne, col)) {
                                        tableau = possible2(tableau, ligne, col);
                                    } else {
                                        tableau = corrige(tableau, ligne, col);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    tableau = corrige(tableau, ligne, col - 1);
                    tableau[ligne][col] = 0;
                    tableau = possible2(tableau, ligne, col);
                    if (tableau[ligne][col] == 0)
                        tableau = corrige(tableau, ligne, col);
                }

            } else {
                tableau = corrige(tableau, ligne, col - 2);
                tableau[ligne][col - 1] = 0;
                tableau = possible2(tableau, ligne, col - 1);
                if (tableau[ligne][col - 1] == 0) {
                    tableau = corrige(tableau, ligne, col - 1);
                    tableau[ligne][col] = 0;
                    tableau = possible2(tableau, ligne, col);
                    if (tableau[ligne][col] == 0)
                        tableau = corrige(tableau, ligne, col);
                } else {
                    tableau[ligne][col] = 0;
                    tableau = possible2(tableau, ligne, col);
                    if (tableau[ligne][col] == 0) {
                        tableau = corrige(tableau, ligne, col);
                    }
                }

            }
        }
        // }
        return tableau;
    }

    public static int[] tGauche(int[][] tableau) {
        int[] tgauche = new int[tableau[0].length];
        for (int i = 0; i < tgauche.length; i++) {
            int g = tableau[i][0];
            for (int j = 0; j < tgauche.length; j++) {
                if (g <= tableau[i][j]) {
                    tgauche[i]++;
                    g = tableau[i][j];
                }
            }
        }
        return tgauche;

    }

    public static int[] tBas(int[][] tableau) {
        int[] tbas = new int[tableau[0].length];
        for (int i = 0; i < tbas.length; i++) {
            int g = tableau[tbas.length - 1][i];
            for (int j = tbas.length - 1; j >= 0; j--) {
                if (g <= tableau[j][i]) {
                    tbas[i]++;
                    g = tableau[j][i];
                }
            }
        }
        return tbas;

    }

    public static int[] tHaut(int[][] tableau) {
        int[] thaut = new int[tableau[0].length];
        for (int i = 0; i < thaut.length; i++) {
            int g = tableau[0][i];
            for (int j = 0; j < thaut.length; j++) {
                if (g <= tableau[j][i]) {
                    thaut[i]++;
                    g = tableau[j][i];
                }
            }
        }
        return thaut;

    }

    public static int[] tDroit(int[][] tableau) {
        int[] tgauche = new int[tableau[0].length];
        for (int i = 0; i < tableau.length; i++) {
            int g = tableau[i][tableau.length - 1];
            for (int j = tableau.length - 1; j >= 0; j--) {
                if (g <= tableau[i][j]) {
                    tgauche[i]++;
                    g = tableau[i][j];
                }
            }
        }
        return tgauche;

    }

    public static void afficheGrille(int[][] tableau, int[][] table) {
        int n = tableau.length;
        int[] thaut = tHaut(table);
        int[] tbas = tBas(table);
        int[] tgauche = tGauche(table);
        int[] tdroit = tDroit(table);
        // String [][] tab = new String[n][n];
        for (int i = 0; i < 2 * n + 5; i++) {
            for (int j = 0; j < 2 * n + 5; j++) {
                if (!(i == 0 || i == 2 * n + 4 || i == 1 || i == 2 * n + 3)
                        || !(j == 0 || j == 1 || j == 2 * n + 4 || j == 2 * n + 3)) {

                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            System.out.print(" +");
                        } else {
                            System.out.print(" -");
                        }
                    } else {
                        if (j % 2 == 0) {
                            System.out.print(" |");
                        } else {
                            if (j < 2 && !(i < 2 || i > 2 * n + 2)) {
                                System.out.print(" " + ((tgauche[(i - 3) / 2] == 0) ? " " : tgauche[(i - 3) / 2]));
                            } else if (j > 2 * n + 2 && !(i < 2 || i > 2 * n + 2)) {
                                // System.out.print(" " + tdroit[(i - 3) / 2]);
                                System.out.print(" " + ((tdroit[(i - 3) / 2] == 0) ? " " : tdroit[(i - 3) / 2]));
                            } else if (i == 1) {
                                // System.out.print(" " + thaut[(j - 3) / 2]);
                                System.out.print(" " + ((thaut[(j - 3) / 2] == 0) ? " " : thaut[(j - 3) / 2]));
                            } else if (i == 2 * n + 3) {
                                // System.out.print(" " + tbas[(j - 3) / 2]);
                                System.out.print(" " + ((tbas[(j - 3) / 2] == 0) ? " " : tbas[(j - 3) / 2]));
                            } else {
                                // System.out.print(" " + tableau[(i - 3) / 2][(j - 3) / 2]);
                                System.out.print(" " + ((tableau[(i - 3) / 2][(j - 3) / 2] == 0) ? " "
                                        : tableau[(i - 3) / 2][(j - 3) / 2]));
                            }
                        }
                    }

                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

    }
}