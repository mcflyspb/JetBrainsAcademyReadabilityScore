package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static int polysyllablesCount = 0;
    private static int syllablesCounts = 0;
    private static float ari = 0;
    private static float fk = 0;
    private static float smog = 0;
    private static float cl = 0;
    private static final String ENTER_SCORE = "Enter the score you want to calculate (ARI, FK, SMOG, CL, all):";
    public static void main(String[] args) throws FileNotFoundException {
        int charactersCount = 0;
        int wordsCounts = 0;
        int sentencesCount = 0;
        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(new File(args[0]));
        while (scanner.hasNext()) {
            text.append(scanner.nextLine().trim());
        }
        scanner.close();
        String[] fullText = text.toString().split("[!?.]");
        sentencesCount = fullText.length;
        String fullTextWoEnds = text.toString().replaceAll("[!?.]", "");
        String[] wordsText = fullTextWoEnds.split(" ");
        wordsCounts = wordsText.length;
        String charactersText = text.toString().replaceAll("[ \n\t]", "");
        charactersCount = charactersText.length();


        ari = getAri(charactersCount, wordsCounts, sentencesCount);
        getSyllables(wordsText);
        fk = getFk(wordsCounts, sentencesCount, syllablesCounts);
        smog = getSmog(polysyllablesCount, sentencesCount);
        cl = getCl(sentencesCount, wordsCounts, charactersCount);

        System.out.println("The text is:");
        System.out.println(text.toString());
        System.out.println();
        System.out.printf("Words: %d\n", wordsCounts);
        System.out.printf("Sentences: %d\n", sentencesCount);
        System.out.printf("Characters: %d\n", charactersCount);
        System.out.printf("Syllables: %d\n", syllablesCounts);
        System.out.printf("Polysyllables: %d\n", polysyllablesCount);

        System.out.println(ENTER_SCORE);
        Scanner userScanner = new Scanner(System.in);
        String userInput = userScanner.nextLine();

        switch (userInput) {
            case ("ARI") -> {
                printAri();
            }
            case ("FK") -> {
                printFk();
            }
            case ("SMOG") -> {
                printSmog();
            }
            case ("CL") -> {
                printCL();
            }
            case ("all") -> {
                printAll();
            }

            default -> {
            }
        }
    }

    private static int getLowYear(double score) {
        return Math.min((int) Math.ceil(score) + 4, 18);
    }


    private static void printAll() {
            printAri();
            printFk();
            printSmog();
            printCL();
    }

    private static void printCL () {
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).\n", cl, getLowYear(cl));
    }

    private static void printSmog() {
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).\n", smog, getLowYear(smog));
    }

    private static void printFk() {
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).\n", fk, getLowYear(fk));
    }

    private static void printAri() {
        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).\n", ari, getLowYear(ari));
    }

    private static float getCl(float sentencesCount, float wordsCounts, float charactersCount) {
        return (float) (0.0588 * charactersCount / (wordsCounts / 100) - 0.296 * sentencesCount / (wordsCounts / 100) - 15.8);
    }

    private static float getSmog(float polysyllablesCount, float sentencesCount) {
        return (float) (1.043 * Math.sqrt(polysyllablesCount * 30 / sentencesCount) + 3.1291);
    }

    private static float getFk(int wordsCounts, int sentencesCount, int syllablesCounts) {
        return (float) (0.39 * wordsCounts / sentencesCount + 11.8 * syllablesCounts / wordsCounts - 15.59);
    }

    private static float getAri(int characters, int words, int sentences) {
        return (float) (4.71 * characters / words + 0.5 * words / sentences - 21.43);
    }

    private static void getSyllables(String[] wordsText) {
        int temp;
        for (String x: wordsText) {
            String[] y = x.split("[aeiouyAEIOUY]+");
            temp = y.length - 1;
            if (temp < 1) {
                temp = 1;
            }
            if (temp > 2) {
                polysyllablesCount++;
            }
            syllablesCounts += temp;
        }
    }
}
