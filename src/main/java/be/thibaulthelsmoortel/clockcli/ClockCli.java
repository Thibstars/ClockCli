package be.thibaulthelsmoortel.clockcli;

import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * @author Thibault Helsmoortel
 */
@SuppressWarnings("squid:S106") // This is a CLI app
@Command(name = "clock", description = "Clock in for work and get notified when to leave.", mixinStandardHelpOptions = true, versionProvider = VersionProvider.class)
public class ClockCli implements Runnable {

    @SuppressWarnings("UnusedDeclaration") // CLI option with default value
    @Option(paramLabel = "WORKDURATION", names = {"--workDuration", "-w"}, description = "Work duration in hours.", arity = "0..1", defaultValue = "8")
    private int workDuration;

    @SuppressWarnings("UnusedDeclaration") // CLI option with default value
    @Option(paramLabel = "BREAKDURATION", names = {"--breakDuration", "-b"}, description = "Break duration in minutes.", arity = "0..1", defaultValue = "45")
    private int breakDuration;

    @Override
    public void run() {
        Timer timer = new Timer();
        LocalDateTime schedule = LocalDateTime.now().plus(workDuration, ChronoUnit.HOURS).plus(breakDuration, ChronoUnit.MINUTES);
        System.out.println("Registration successful. You may leave at: " + schedule.toString());

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Toolkit.getDefaultToolkit().beep();
                System.out.println("You can now clock out. See you later.");
                System.exit(0);
            }
        }, Date.from(schedule.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static void main(String[] args) {
        new CommandLine(new ClockCli()).execute(args);
    }
}
