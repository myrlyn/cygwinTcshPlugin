package cygwinTcshPlugin;

import java.io.File;
import java.util.logging.Level;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.kodedu.terminalfx.config.TabNameGenerator;
import com.kodedu.terminalfx.config.TerminalConfig;

import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import tabuterminal.TabuTerminal;
import tabuterminal.TabuTerminalPlugin_V1;

public class CygwinTCSHPlugin extends TabuTerminalPlugin_V1 {
	TerminalConfig tconf = new TerminalConfig();
	TerminalBuilder tbuild = null;
	MenuItem TCSHTabItem = new MenuItem("New TCSH Tab");
	String defaultTerminalCommand = "C:\\cygwin64\\bin\\tcsh.exe -l";
	public CygwinTCSHPlugin(TabuTerminal jtt) {
		super(jtt);
	}

	@Override
	public void initialize(String jf) {
		tconf.setBackgroundColor(Color.rgb(16, 16, 16));
		tconf.setForegroundColor(Color.rgb(240, 240, 240));
		tconf.setCursorColor(Color.rgb(255, 0, 0, 0.5));
		tconf.setWindowsTerminalStarter(defaultTerminalCommand);
		tconf.setCopyOnSelect(true);
		tbuild = new TerminalBuilder(tconf);
		if (!(new File("C:\\cygwin64\\bin\\tcsh.exe").canExecute())){
			this.getTerminalWindow().getLogger().log(Level.SEVERE,"CANNOT EXECUTE C:\\cygwin64\\bin\\tcsh.exe!  Is cygwin tcsh properly installed?");
			return;
		}
		tbuild.setNameGenerator(new TabNameGenerator() {
			
			private long tabNumber = 1;

			public String next() {
				// TODO Auto-generated method stub
				return "TCSH: "+tabNumber;
			}

			

		});
		this.getTerminalWindow().getTabMenu().getItems().add(TCSHTabItem);
		TCSHTabItem.setOnAction(evt -> {
			TerminalTab  tab = tbuild.newTerminal();
			this.getTerminalWindow().getTabPane().getTabs().add(tab);
			this.getTerminalWindow().getTabPane().getSelectionModel().select(tab);
		});
	
	}

	@Override
	public void removePlugin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return null;
	}

}
