package SecondProject;

import javax.swing.JFrame;


public class Therapy_ShowGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				Therapy_GUI tgui = new Therapy_GUI();
				tgui.setSize(900, 600);
				tgui.setTitle("THERAPY");
				tgui.setVisible(true);
				tgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
			
		});
	} //Close main

}


