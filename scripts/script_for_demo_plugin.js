
/**
http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
http://www.java2s.com/Tutorial/Java/0240__Swing/WhatistheBorderLayout.htm
*/

var imports = new JavaImporter(
	java.awt.BorderLayout,
	java.awt.Container,
	java.awt.Font,
	java.awt.Dimension,
	javax.swing.BorderFactory,
	javax.swing.JFrame,
	javax.swing.JPanel,
	javax.swing.JLabel,
	javax.swing.border.BevelBorder,
	
	javax.swing.JEditorPane,
	javax.swing.JScrollPane,
	javax.swing.ScrollPaneConstants,
	
	javax.swing.WindowConstants,
	
	org.gjt.sp.jedit.jEdit,
	org.gjt.sp.jedit.View
	);

with (imports) {
	
	function mainFrame(panel) {
		// frame, the main panel is enclosed in it
		var frm = new JFrame("Test Frame");
		frm.getContentPane().add(panel, BorderLayout.CENTER);
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//frm.setSize(400, 400);
		frm.pack();
		frm.setVisible(true);
	}
	
	function demoBorder(anyargs) {
		var pane = new JPanel();
		pane.setLayout(new BorderLayout());
		var labelN = new JLabel("North", JLabel.CENTER);
		labelN.setFont(new Font("Courier", Font.BOLD, 36));
		labelN.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pane.add(labelN, BorderLayout.NORTH);
		var labelS = new JLabel("South", JLabel.CENTER);
		labelS.setFont(new Font("Courier", Font.BOLD, 36));
		labelS.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pane.add(labelS, BorderLayout.SOUTH);
		var labelE = new JLabel("East", JLabel.CENTER);
		labelE.setFont(new Font("Courier", Font.BOLD, 36));
		labelE.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pane.add(labelE, BorderLayout.EAST);
		var labelW = new JLabel("West", JLabel.CENTER);
		labelW.setFont(new Font("Courier", Font.BOLD, 36));
		labelW.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pane.add(labelW, BorderLayout.WEST);
		var labelC = new JLabel("Center", JLabel.CENTER);
		labelC.setFont(new Font("Courier", Font.BOLD, 36));
		labelC.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pane.add(labelC, BorderLayout.CENTER);
		pane.setPreferredSize(new Dimension(400, 300));
		//
		return pane;
	}

	/*
	* Demonstrates the use of javax.swing.JScrollPane.
	*
	* @author Roedy Green, Canadian Mind Products
	* @version 1.0 2011-09-20 initial version
	* @since 2011-09-20
	* Licence: This software may be copied and used freely for any purpose but military.
	* http://mindprod.com/contact/nonmil.html
	*/
	
	var demoScroller = function(addtext) {
		var fview;
		var cbuffer;
		var jEditorPane = new JEditorPane();
		jEditorPane.setContentType("text/html");
		jEditorPane.setEditable(false);
		var text =
			"<html lang=\"en-CA\"><body>The destructive Seven Blunders " +
			"of the World that cause violence:\n" +
			"<ol><li>Wealth without work.</li>\n" +
			"<li>Pleasure without conscience.</li>\n" +
			"<li>Knowledge without character.</li>\n" +
			"<li>Commerce without morality.</li>\n" +
			"<li>Science without humanity.</li>\n" +
			"<li>Religion without sacrifice.</li>\n" +
			"<li>Politics without principle.</li></ol>\n" +
			"~ Mahatma Gandhi\n</body></html>";
		if (addtext) {
			fview = jEdit.getFirstView();
			cbuffer = fview.getBuffer();
			text = text.replace("\n</body></html>", "<br/><br/>" + cbuffer + "\n</body></html>");
		}
		jEditorPane.setText(text);
		//
		var scroller = new JScrollPane(
			jEditorPane,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			//JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
		scroller.getVerticalScrollBar().setUnitIncrement(16);
		scroller.getVerticalScrollBar().setValue(0);
		// Using the default VERTICAL_SCROLLBAR_AS_NEEDED will use scrollbars
		// even when not strictly needed. If you want precise control
		// you must decide for yourself dynamically.
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//
		var pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(scroller, BorderLayout.CENTER);
		return pane;
	}
	
	// The results of these functions in the (standalone) script 
	// are visualized in a frame as follows:
	
	//mainFrame(demoBorder());
	//mainFrame(demoScroller());
	//mainFrame(demoScroller(true));
	
	// These should be commented out when 
	// script is used in the NashornFuncWrapper plugin.
	
	
	
}