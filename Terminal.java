
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.BadLocationException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
public class Terminal extends javax.swing.JInternalFrame {
    String[] s=new String[5];
    String path=new String();
    int caretpos;
    /**
     * Creates new form Terminal
     */
    public Terminal() {
        initComponents();
        path="/home/ravi";
        ta1.append("ravi-x552ld:~>");
        caretpos=14;
        addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosed(InternalFrameEvent e) {
                System.out.print("hello");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta1 = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Terminal");
        setFrameIcon(null);

        ta1.setBackground(new java.awt.Color(27, 8, 8));
        ta1.setColumns(20);
        ta1.setForeground(new java.awt.Color(31, 220, 112));
        ta1.setRows(5);
        ta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ta1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ta1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ta1KeyPressed
        // TODO add your handling code here:
        int caretpos1 = ta1.getCaretPosition();
        //System.out.println(caretpos+" "+caretpos1);
        if(caretpos1>=caretpos)
        {
            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && caretpos1==caretpos) {
                evt.consume();
               // System.out.println(caretpos+"*"+caretpos1);
                ta1.setEditable(false);
            }
            else
                ta1.setEditable(true);
        }
        else
           ta1.setEditable(false); 
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();
            /*String str=ta.getText();
            str=str.substring(str.lastIndexOf('>')+1);
            if(str.length()>1)
                ta.append("\n"+str);
            ta.append("\n"+"ravi-x552ld>");
   //        int caretpos = ta.getCaretPosition();
     //      ta.moveCaretPosition(caretpos);*/
            caretpos = ta1.getCaretPosition();
            try {
                int linenum=ta1.getLineOfOffset(caretpos);
                int initial=ta1.getLineStartOffset(linenum);
                ta1.select(initial, caretpos);
                String str=ta1.getSelectedText();
                ta1.select(caretpos,caretpos);
                str=str.substring(str.lastIndexOf('>')+1);
                caretpos=caretpos+15;
                if(str.length()>0)
                {
                    
                    s=str.split(" ");
                    //System.out.println(s[0].equals("cd"));
                    
                   
                    if(s[0].equals("cd"))
                    {
                        //System.out.println("cd");
                        if(s.length>1){
                            if(s[1].equals("..")){
                                if(path.equals("/home/ravi"))
                                {
                                    ta1.append("\n"+"ravi-x552ld:~>");
                                }
                                else
                                {
                                    path=path.substring(0,path.lastIndexOf("/"));
                                    if("/home/ravi".equals(path))
                                    {
                                        ta1.append("\n"+"ravi-x552ld:~>");
                                    }
                                    else
                                    {
                                        ta1.append("\n"+"ravi-x552ld:~"+path.substring(path.lastIndexOf("/"))+">");
                                    }
                                }
                            }
                            else  {  
                                String p=path;
                                p= p.concat("/");
                                p=p.concat(s[1]);
                                //ta.append("\n"+p);
                                File folder = new File(p);
                                if(folder.exists())
                                {
                                    path=p;
                                    ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                                }
                                else
                                {
                                    ta1.append("\n"+"No such file or directory");
                                    if("/home/ravi".equals(path))
                                    {
                                        ta1.append("\n"+"ravi-x552ld:~>");
                                    }
                                    else
                                    {
                                        ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                                    }
                                }
                            }
                            
                        }
                        else
                        {
                            ta1.append("\n"+"Invalid syntax");
                            if("/home/ravi".equals(path))
                                {
                                    ta1.append("\n"+"ravi-x552ld:~>");
                                }
                                else
                                {
                                    ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                                }
                        }
                    }
                    else if(s[0].equals("ls"))
                    {
                       // System.out.println("ls");
                        File folder = new File(path);
                        File[] listOfFiles = folder.listFiles();
                        if(listOfFiles.length>0){
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if(listOfFiles[i].isFile())
                                ta1.append("\n"+"file"+" "+listOfFiles[i].getName());
                            else if(listOfFiles[i].isDirectory())
                                ta1.append("\n"+"directory"+" "+listOfFiles[i].getName()); 
                        }
                        }
                        else
                        {
                            ta1.append("\n"+"No file or directory");
                        }
                        if("/home/ravi".equals(path))
                        {
                            ta1.append("\n"+"ravi-x552ld:~>");
                        }
                        else
                        {
                            ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                        }
                    }
                    else if(s[0].equals("pwd"))
                    {
                        //System.out.println("pwd");
                        /*System.out.println("hello");
                       
                        Process proc = Runtime.getRuntime().exec("pwd");
                        // Read the output
                        BufferedReader reader =new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = "";
                        while((line = reader.readLine()) != null) {
                            ta.append("\n"+line);
                        }*/
                        ta1.append("\n"+path);
                        if("/home/ravi".equals(path))
                        {
                            ta1.append("\n"+"ravi-x552ld:~>");
                        }
                        else
                        {
                            ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                        }
                    }
                    else if(s[0].equals("Coolpad"))
                    {
                        if(s.length>1){
                            String p=path;
                            p=p.concat("/");
                            p=p.concat(s[1]);
                            
                            try {
                                Myhome.opencoolpad(p);
                            } catch (IOException ex) {
                                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //cp.show();
                            System.out.println("terminal");
                            if("/home/ravi".equals(path))
                            {
                                ta1.append("\n"+"ravi-x552ld:~>");
                            }
                            else
                            {
                                ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                            }
                        
                    }
                    }
                    else if(s[0].equals("play"))
                        {
                            System.out.println("terminal");
                            if(s.length>1){
                                String p=path;
                                p=p.concat("/");
                                p=p.concat(s[1]);
                                
                                
                                
                                try {
                                    Myhome.openplayer(p);
                                } catch (IOException ex) {
                                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                //cp.show();
                                System.out.println("terminal");
                                if("/home/ravi".equals(path))
                                {
                                    ta1.append("\n"+"ravi-x552ld:~>");
                                }
                                else
                                {
                                    ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                                }
                        
                            }
                            else
                            {
                            ta1.append("\n"+"Invalid syntax");
                            if("/home/ravi".equals(path))
                                {
                                    ta1.append("\n"+"ravi-x552ld:~>");
                                }
                                else
                                {
                                    ta1.append("\n"+"ravi-x552ld:~"+path.substring(10)+">");
                                }
                            }
                    
                        }
                
                
            } 
            } catch (BadLocationException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
           }
    }//GEN-LAST:event_ta1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta1;
    // End of variables declaration//GEN-END:variables
}
