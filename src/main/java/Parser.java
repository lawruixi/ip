import java.util.regex.Matcher;

public class Parser {
    /**
     * Returns true if the command would cause Duchess to exit operations.
     *
     * @param s - the command to check for exit operations
     * @return    whether the command is an exit command.
     */
    public static boolean isExitCommand(String s) {
        return Utility.matchesRegex(s, "^bye$", true) || Utility.matchesRegex(s, "^exit$", true);
    }


    /**
     * Returns true if the command is recognized as a "list text" command.
     *
     * @param s - the command to check for "list text" command.
     * @return    whether the command is a list tasks command.
     */
    public static boolean isListTasksCommand(String s) {
        return Utility.matchesRegex(s, "^list$");
    }


    /**
     * Returns true if the command is recognized as a "mark task" command.
     *
     * @param s - the command to check for "mark task" command.
     * @return    whether the command is a mark task command.
     */
    public static boolean isMarkTaskCommand(String s) {
        return Utility.matchesRegex(s, "^mark");
    }

    /**
     * Parses a mark task command, returning the index of the task to be marked.
     *
     * @param s - the command to parse for "mark task" command.
     * @return    an integer describing the index of the task to be marked.
     */
    public static int parseMarkTaskCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^mark( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to mark... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Returns true if the command is recognized as an "unmark task" command.
     *
     * @param s - the command to check for "unmark task" command.
     * @return    whether the command is an unmark task command.
     */
    public static boolean isUnmarkTaskCommand(String s) {
        return Utility.matchesRegex(s, "^unmark");
    }

    /**
     * Parses an unmark task command, returning the index of the task to be unmarked.
     *
     * @param s - the command to parse for "unmark task" command.
     * @return    an integer describing the index of the task to be unmarked.
     */
    public static int parseUnmarkTaskCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^unmark( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to unmark... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Returns true if the command is recognized as an "delete task" command.
     *
     *
     * @param s - the command to check for "delete task" command.
     * @return    whether the command is an delete task command.
     */
    public static boolean isDeleteTaskCommand(String s) {
        return Utility.matchesRegex(s, "^delete");
    }

    /**
     * Parses a delete task command, returning the index of the task to be deleted.
     *
     * @param s - the command to parse for "delete task" command.
     * @return    an integer describing the index of the task to be deleted.
     */
    public static int parseDeleteTaskCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^delete( [0-9]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, I don't know which task to delete... ;-;");
        }

        return Integer.parseInt(m.group(1).trim());
    }

    /**
     * Returns true if the command is recognized as a "todo" command.
     *
     * @param s - the command to check for "todo" command.
     * @return    whether the command is recognized as a ToDo command.
     */
    public static boolean isToDoCommand(String s) {
        return Utility.matchesRegex(s, "^todo");
    }

    /**
     * Parses a ToDo command, returning a ToDo task that was parsed from the command.
     *
     * @param s - the command to parse for "todo" command.
     * @return    the ToDo task.
     */
    public static ToDo parseToDoCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^todo( [A-Za-z0-9 ]+)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, todo names cannot be empty... ;-;");
        }

        return new ToDo(m.group(1).trim());
    }

    /**
     * Returns true if the command is recognized as an "deadline" command.
     *
     * @param s - the command to check for "deadline" command.
     * @return    whether the command is recognized as a Deadline command.
     */
    public static boolean isDeadlineCommand(String s) {
        // This mmatches the start of a string, then the word "deadline", then anything afterwards.
        return Utility.parseRegex(s, "^deadline").find(0);
    }

    /**
     * Parses a deadline command, returning the deadline object parsed from the command.
     *
     * @param s - the command to parse for "deadline" command.
     * @return    the Deadline task.
     */
    public static Deadline parseDeadlineCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^deadline( [A-Za-z0-9 ]+)?( /by ([0-9\\-]+)?)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, deadline names cannot be empty... ;-;");
        }
        if (m.group(2) == null || m.group(3) == null) {
            throw new DuchessException("(´；ω；`) Sorry, deadlines must have a deadline... ;-;");
        }

        return new Deadline(m.group(1).trim(), m.group(3).trim());
    }

    /**
     * Returns true if the command is recognized as an "event" command.
     *
     * @param s - the command to check for "event" command.
     * @return    whether the command is recognized as an "event" command.
     */
    public static boolean isEventCommand(String s) {
        return Utility.matchesRegex(s, "^event");
    }

    /**
     * Parses a event command, returning the event object parsed from the command.
     *
     * @param s - the command to parse for "event" command.
     * @return    the Event task.
     */
    public static Event parseEventCommand(String s) throws DuchessException {
        Matcher m = Utility.parseRegex(s, "^event( [A-Za-z0-9 ]+)?( /from( [A-Za-z0-9 ]+)?)?( /to( [A-Za-z0-9 ]+)?)?$");

        if (m.group(1) == null) {
            throw new DuchessException("(´；ω；`) Sorry, event names cannot be empty... ;-;");
        }
        if (m.group(2) == null || m.group(3) == null) {
            throw new DuchessException("(´；ω；`) Sorry, events must have a start time... ;-;");
        }
        if (m.group(4) == null || m.group(5) == null) {
            throw new DuchessException("(´；ω；`) Sorry, events must have an end time... ;-;");
        }

        return new Event(m.group(1).trim(), m.group(3).trim(), m.group(5).trim());
    }

}
