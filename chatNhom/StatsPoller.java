package chatNhom;

import javax.swing.SwingUtilities;
public class StatsPoller implements Runnable {
    private final ChatDashboard dashboard;

    public StatsPoller(ChatDashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void run() {
        while (true) {
            String line = dashboard.fetchStats();
            SwingUtilities.invokeLater(() -> dashboard.update(line));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}