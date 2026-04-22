import apply.MyQuackify;
import apply.StaticQuackify;
import refactor.MyLinkedList;
import refactor.StaticEndlessLinkedList;

import java.util.Random;

/**
 * Entry point for accessing your project 1 files.
 *
 * @author Biruk Tensae
 * @version 1.0
 * @userid btensae22
 * @GTID 904097324
 * <br>
 * <p>
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 * <p>
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 * <p>
 * <br>
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for the contents of all
 * submitted files. If this is left blank, this project will lose
 * points.
 *<p>
 *<br>
 * Agree Here: I agree
 */
public class Main {

    /**
     * Creates and returns a new instance of your class implementing
     * {@link StaticEndlessLinkedList}.
     *
     * @param <T> the type of data
     * @return a new {@link StaticEndlessLinkedList} instance
     * @apiNote This method must be implemented for unit tests to run
     */
    public static <T> StaticEndlessLinkedList<T> getEndlessLinkedListInstance() {
        return new MyLinkedList<>();
    }

    /**
     * Creates and returns a new instance of your class implementing
     * {@link StaticQuackify}.
     *
     * @return a new {@link StaticQuackify} instance
     * @apiNote This method must be implemented for unit tests to run
     */
    public static StaticQuackify getQuackifyInstance() {
        return new MyQuackify();
    }

    /**
     * Creates and returns a new instance of your class implementing
     * {@link StaticQuackify} with a set seed.
     *
     * @param rand the random seed
     * @return a new {@link StaticQuackify} instance with a set seed
     * @apiNote This method must be implemented for unit tests to run
     */
    public static StaticQuackify getQuackifyInstance(Random rand) {
        return new MyQuackify(rand);
    }
}
