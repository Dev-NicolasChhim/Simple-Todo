// Importation des outils
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class simpletodo {

    // Fonction qui affiche le tableau de bord
    public static void Affichage () {
        System.out.println('\n' + "Bienvenue sur Simple Todo" + '\n');
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Marquer une tâche comme 'fait'");
        System.out.println("3. Marquer une tâche comme 'non fait");
        System.out.println("4. Supprimer une tâche");
        System.out.println("5. Visionner la liste des tâches" + '\n');
    }

    // Fonction qui permet d'ajouter une tâche
    public static void ajout (Scanner input){
        System.out.println(" Quel est le nom de la tache ?");
        String taskname = input.nextLine();
        try {
            FileWriter writer = new FileWriter("Tasks.txt", true);
            writer.write("X " + taskname + "\n");
            writer.close();
            System.out.println("La tâche à bien été enregistrée !");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture.");
        }
        System.out.println("Appuiez sur Entrée pour continuer...");
        input.nextLine();
        Affichage ();
    }

    public static ArrayList<String> extraction_liste (){
        ArrayList<String> tasks = new ArrayList<>();
        File file = new File("Tasks.txt");
        if (file.exists()) {
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    tasks.add(fileScanner.nextLine());
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Erreur : fichier introuvable !");
            }
        }
        return tasks;
    }

    public static void Affichage_task (ArrayList<String> tasks){
        System.out.println("Liste de vos tâches :" + '\n');
        for (int i = 0 ; i < tasks.size() ; i++ ){
            System.out.println(tasks.get(i));
        }
    }

    public static void selection (int choix, Scanner input){
        String dispo = "Désolé cette fonctionnalité n'est pas encore disponible.";
        switch (choix) {
            case 1: ajout(input); break;
            case 2: System.out.println(dispo); break;
            case 3: System.out.println(dispo); break;
            case 4: System.out.println(dispo); break;
            case 5: Affichage_task(extraction_liste()); break;
            default:
                System.out.println("Erreur : Aucun choix3 n'a été fait.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choix;
        do {
            Affichage();
            choix = Integer.parseInt(input.nextLine());
        } while (choix <= 1 || choix >= 5);
        selection(choix, input);
        input.close();
    }
}
