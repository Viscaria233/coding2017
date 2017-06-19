package ood.ocp;

public class Logger {

    private LogFormatter formatter;
    private LogSender sender;

    public Logger(LogFormatter formatter, LogSender sender) {
        this.formatter = formatter;
        this.sender = sender;
    }

    public void log(String msg) {
        String logMsg = formatter.format(msg);
        sender.send(logMsg);
    }
}

