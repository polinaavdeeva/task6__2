package ru.vsu.cs.avdeeva_p_a;

import utils.ArrayUtils;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ConsoleMain {
    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 1) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int s = FindPairs.readSum();
        CmdParams params = parseArgs(args);

        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  --start  // sort repeated nums ascending");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            GUIMain.winMain();
        } else {

            int[] pairs = ArrayUtils.readIntArrayFromFile(params.inputFile);
            FindPairs.findPairs(pairs, s);
        }
    }
}