package org.processmining.plugins;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.fluxicon.slickerbox.factory.SlickerDecorator;
import com.fluxicon.slickerbox.factory.SlickerFactory;
 
public class CheckBoxDemo extends JPanel implements ItemListener{
 
	int attributeNum;
	int controlFlowRuleNum=4;
	
	JLabel lControlFlow = new JLabel("ControlFlow");
    JCheckBox []cControlFlow; 
    JTextField []tControlFlowWeight;
    String []ruleSet = {"DirectlyFollow","TraceLength","Activity","Pattern"};
    
    
	JLabel lDataFlow = new JLabel("DataFlow");
    JCheckBox []cData; 
    JTextField []tDataWeight;
    
    JCheckBox Jok = new JCheckBox("OK");
   
    public CheckBoxDemo(int attributeCount) {
      
       super(new BorderLayout());
       attributeNum=attributeCount;
       

	   SlickerFactory factory = SlickerFactory.instance();
	   SlickerDecorator decorator = SlickerDecorator.instance();
	   JPanel rulePanel = factory.createRoundedPanel(15, Color.gray);
		
       //璁剧疆涓�涓猵anel锛屽皢澶嶉�夋鏀惧叆鍚屼竴涓猵anel
       JPanel controlFlowPanel = new JPanel(new GridLayout(0,2));
       cControlFlow = new JCheckBox[controlFlowRuleNum]; 
       tControlFlowWeight = new JTextField[controlFlowRuleNum]; 
       for(int i=0;i<controlFlowRuleNum;i++)
       {
    	   cControlFlow[i] = new JCheckBox(ruleSet[i]);
	       controlFlowPanel.add(cControlFlow[i]);
	       tControlFlowWeight[i] = new JTextField();
	       controlFlowPanel.add(tControlFlowWeight[i]);   
       }
       controlFlowPanel.add(Jok);
       JPanel dataFlowPanel = new JPanel(new GridLayout(0,2));
       cData = new JCheckBox[attributeNum]; 
       tDataWeight = new JTextField[attributeNum];
       for(int i=0;i<attributeNum;i++) 
       {
    	   cData[i] = new JCheckBox("attribute"+i); 
    	   dataFlowPanel.add(cData[i]);
    	   tDataWeight[i] = new JTextField(); 
    	   dataFlowPanel.add(tDataWeight[i]);
       }
       JScrollPane dataFlowScroll=new JScrollPane(dataFlowPanel);
       
     //设置rulepanel中的元素             
       JPanel controlFlowTitle = new JPanel();
       controlFlowTitle.add(lControlFlow);
       controlFlowTitle.setBackground(Color.gray);
       controlFlowTitle.setBounds(50, 30, 100, 20);
       
       JPanel dataTitle = new JPanel();
       dataTitle.add(lDataFlow);
       dataTitle.setBackground(Color.gray);
       dataTitle.setBounds(300, 30, 100, 20);
       
 
       controlFlowPanel.setBackground(Color.BLUE);
       controlFlowPanel.setBounds(50, 50, 200, 100);
       

       dataFlowScroll.setBackground(Color.yellow);
       dataFlowScroll.setBounds(300, 70, 200, 100);
       
       rulePanel.setLayout(null);
       rulePanel.add(controlFlowTitle);
       rulePanel.add(dataTitle);
       rulePanel.add(controlFlowPanel);
       rulePanel.add(dataFlowScroll);

       rulePanel.setBounds(0, 0, 500, 250);
       rulePanel.setBackground(Color.green);
       //灏嗗閫夋鐨刾anel娣诲姞鍒伴潰鏉跨殑宸﹁竟
       setLayout(null);
       add(rulePanel,BorderLayout.WEST);

       //add the monitor
       for(int i=0;i<controlFlowRuleNum;i++)
       {
    	   cControlFlow[i].addItemListener(this);
       }
       
       for(int i=0;i<attributeNum;i++)
       {
    	   cData[i].addItemListener(this);
       }
       
       Jok.addItemListener(this);

      
       //璁剧疆闈㈡澘鐨勮竟鐣岋紝浣垮緱鎺т欢鑳藉涓庤竟鐣屾湁涓�瀹氳窛绂�
       setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      

    }
   
 
    private ImageIcon createImageIcon(String string) {
       URL url = CheckBoxDemo.class.getResource(string);
       if(url != null)
           return new ImageIcon(url);
       else
           System.out.println("image "+string +"not exist!");
       return null;
    }
 
    public static void createAndShowGUI()
    {
       JFrame frame = new JFrame("Hello world");
       frame.setLayout(null);
       JComponent panel = new CheckBoxDemo(6);
       panel.setBackground(Color.red);
       panel.setBounds(0, 0, 600, 300);
       frame.add(panel);
       frame.pack();
       frame.setSize(600, 400);
       frame.setVisible(true);
      
    }
   
    public static void main(String[] args) {
      
       SwingUtilities.invokeLater(new Runnable() {
          
//           @Override
           public void run() {
              createAndShowGUI();
           }
       });
    }

 
    //鎺ュ彈澶勭悊澶嶉�夋鐐瑰嚮浜嬩欢
    @Override
    public void itemStateChanged(ItemEvent e) {
       //鑾峰彇鏀瑰彉鐨勫閫夋寜閿�
       Object source = e.getItemSelectable();
       for(int i=0;i<controlFlowRuleNum;i++)
       {
    	   if(source == cControlFlow[i])
           {
               if(e.getStateChange() == ItemEvent.DESELECTED)
            	   tControlFlowWeight[i].setEditable(false);
               else 
              {
            	   tControlFlowWeight[i].setText(null); 
            	   tControlFlowWeight[i].setEditable(true);
              }
           }
       }

       for(int i=0;i<attributeNum;i++)
       {
    	   if(source == cData[i])
           {
//    		   JOptionPane.showMessageDialog(null, "hello", "world", JOptionPane.ERROR_MESSAGE);
               if(e.getStateChange() == ItemEvent.DESELECTED)
            	   tDataWeight[i].setEditable(false);
               else 
              {
            	   tDataWeight[i].setText(null); 
            	   tDataWeight[i].setEditable(true);
              }
           }
       }
       
       if(source == Jok)
       {
           for(int i=0;i<controlFlowRuleNum;i++)
           {
        	   if(tControlFlowWeight[i].isEditable() == true);
    	       
           }
       }
       
    }
}