package pi.SmetaCalc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

//����� �����
public class Store extends JPanel{
	//��������� ����������� �� ��������
	static final int TO_SMETA=0;
	static final int ADD_TO_SMETA=1;
	
	
//������� ����������� ������� ���������� � ����������	
	private JSplitPane content;	
//������� �����, ��� ������������� ������� � �����������
	private 	JPanel pntop;
		//������ ����������� ��� ������� table
	private 		JScrollPane scrollPane;
		//������� ��� ��������� ������ ����������
	private 	JMultiTable table;
		//������ � ��������
	private 	JPanel navpanel;
			//������ ��� �������� � �����
	private 		JButton btsmeta;
			//������ ���������� ��������� � �����
	private 		JButton btadd;
			//������ �������� ������ ���������
	private 		JButton btcreate;
			//������ �������� ��������� �� ������
	private 		JButton btdelete;
//������ ����� ��� ������������� ������� � ����������� ������ ���������
	private 	JPanel pnbottom;
		//������ ����������� ��� ������� table2
	private 	JScrollPane scrollPane2;
		//������� ��� ��������� ������ ���������� ������ ���������	
	private 	JMultiTableTwo table2;
		//������ � ��������
	private 	JPanel navpanel2;
			//������� F
	private 		JLabel lbfunction;
			//��������� ���� ��� ����� ������� ��������� ���������
	private 		JTextField tffunction;
			//������ ���������� ������ ���������
	private 		JButton btedit;
			//������ �������� ���������
	private 		JButton btparamdel;
			//������ ���������, ���������� ��������� ������
	private 		JButton btparamedit;
			//������ ��������� ��� �������� ������� ����������(table)
	private String[] columnNames = {
	           "���",
	           "������������",
	           "�����"
	     };
	//������ ��������� ��� �������� ������� �����������(table2)
	private String[] columnNames2={"��������","���","���","��������"};
	
	//���������� ������ ����������� ���������
	private int selectmaterial=-1;
	//���������� ������ ����������� ���������
	private int selectparametr=-1;       
	
	//������ ����������
	private ArrayList<Material> materials=new ArrayList<Material>();		
	//���������� ��������� ��������
	Material getActionMaterial(){
		if(selectmaterial>=0&&selectmaterial<materials.size()){
			return materials.get(selectmaterial);
		}else{
			return null;
		}
	}
	//���������� ��� ���������� ����-������ ����������
	void createdemo(){
		Material m=new Material(0,"�����(EROS_RS286)" ,"�����-������" , new ArrayList<Parametr>(), "stoimost",1);
		m.addParametr(new Parametr("���������", "stoimost", TypeName.floatType, "303.16"));
		materials.add(m);
		
		m=new Material(1,"����" ,"������" , new ArrayList<Parametr>(), "w*h*length*2.5",1);
		m.addParametr(new Parametr("�����(�)", "length", TypeName.floatType, "5.10"));
		m.addParametr(new Parametr("������(��)", "w", TypeName.floatType, "8"));
		m.addParametr(new Parametr("������(��)", "h", TypeName.floatType, "4"));
		materials.add(m);
		reBuild();
	}
	//��� ��������� ������� ���� ���������� ����� ����� ������ � ���������� ������
	void reswin(){
		// TODO Auto-generated method stub
		content.setBounds(300, 0, getWidth()-300, getHeight());
		
		navpanel.setBounds(0,pntop.getHeight()-30, pntop.getWidth(), 30);
		navpanel2.setBounds(0,pnbottom.getHeight()-60, pnbottom.getWidth(), 60);
		scrollPane.setBounds(0, 0, pntop.getWidth(), pntop.getHeight()-30);
		scrollPane2.setBounds(0, 0, pnbottom.getWidth(), pnbottom.getHeight()-60);
		btdelete.setBounds(pntop.getWidth()-100, 0, 100, 30);
		btedit.setBounds(pnbottom.getWidth()-100, 30, 100, 30);
		tffunction.setBounds(30, 0, navpanel2.getWidth()-30, 30);		
		if(pntop.getHeight()<content.getHeight()-200){
					
		}else{
					
			content.setDividerLocation(content.getHeight()-200);
		}
		validate();
	}
	//�������� ��������
	void selectParametr(int index) {
		if(index>=0){
			selectparametr=index;
			
		}
	}
	//���������� ����� ������������ �������� �������� � ���������� ��� ���������
	void selectMaterial(int index){
		//���� ������ � ������ ���������
		if(index>=0&&index<materials.size()){
			selectmaterial=index;
			Material m=materials.get(index);
			ArrayList<Parametr> params=m.getParametrs();
			//� data ������������ ��������� ����������� ���������
			String[][] data=new String[params.size()+4][];
			data[0]=new String[4];
			data[0][0]="���";
			//� ������� ����� �� ������������
			data[0][1]="null";
			data[0][2]=TypeName.intType.toString();
			data[0][3]=String.valueOf(m.getID());
			data[1]=new String[4];
			data[1][0]="������������";
			//� ������� ����� �� ������������
			data[1][1]="null";
			data[1][2]=TypeName.stringType.toString();
			data[1][3]=m.getName();
			data[2]=new String[4];
			data[2][0]="�����";
			//� ������� ����� �� ������������
			data[2][1]="null";
			data[2][2]=TypeName.stringType.toString();
			data[2][3]=m.getClassname();
			data[3]=new String[4];
			data[3][0]="���������";
			//� ������� ����� �� ������������
			data[3][1]="null";
			data[3][2]=TypeName.intType.toString();
			data[3][3]=String.valueOf(m.getCount());
			//��������� ������� ������� �������
			for(int i=0;i<params.size();i++){
				Parametr pp=params.get(i);
				data[i+4]=new String[4];
				//������������ ���������
				data[i+4][0]=pp.getCaption();
				//��� ��� ������������ � �������
				data[i+4][1]=pp.getName();
				//��� ���������
				data[i+4][2]=pp.getType().toString();
				//�������� ���������
				data[i+4][3]=pp.getValue();
				
			}
			//��������� ���������� �������
			table2.setContent(data, columnNames2);
			//������� ���������� ������
		    pnbottom.removeAll();
		    pnbottom.add(scrollPane2);
		    pnbottom.add(navpanel2);
		    tffunction.setText(m.getFormula());
		      //������������� �����
		      reswin();
		}
	}
	//������� ����� ��������
	void addMaterial(){
		int id=materials.size();
		Material m=new Material(id,"���","��� ������",new ArrayList<Parametr>(),"",1);
		materials.add(m);
		reBuild();
		reswin();
	}
	//��������� ����� �������� � ����������� ���������
	void addParametr(int index) {
		if(index>=0&&index<materials.size()){
			Material m=materials.get(index);
			m.addParametr(new Parametr("�����","name",TypeName.intType,"0"));
			selectMaterial(index);
		}
	}
	//������� ���������� ��������
	void deleteParametr(int indexm,int indexp) {
		if(indexm>=0&&indexm<materials.size()&&indexp>=0){
			Material m=materials.get(indexm);
			m.deleteParametr(indexp);
			selectMaterial(indexm);
		}
	}
	//������ ��������� ��� ���������
	void setParametrs(){
		if(selectmaterial>=0&&selectmaterial<materials.size()){
			String data[][]=table2.getData();
			//���� ���������� ��������
			if(data!=null) {
				 ArrayList<Parametr> paramlist= new ArrayList<Parametr>();
				 //��������� ������ ����������
				 for(int i=4;i<data.length;i++) {
					 Parametr p=new Parametr(data[i][0], data[i][1], new TypeName(data[i][2]), data[i][3]);
					 paramlist.add(p);
				 }
				//�������� �������(�������� ��� ���,���������, ��������� � �.�
				materials.get(selectmaterial).edit(Integer.valueOf(data[0][3]), data[1][3], data[2][3], paramlist,tffunction.getText(), Integer.valueOf(data[3][3]));;
				reBuild();
				reswin();
			}
			
			
		}
	}
	//������� ��������� ��������
	void deleteMaterial(int index) {
		 if(index>=0&&index<materials.size()){
			 materials.remove(index);
			 reBuild();
			 reswin();
		 }
		 
	}
	
	//��� ������ ����� ������ ����������� ������� ����������
	void reBuild(){
		//����� ������������ � data
		String[][] data=new String[materials.size()][];
		for(int i=0;i<materials.size();i++){
			Material m=materials.get(i);
			data[i]=new String[3];
			//���� ���
			data[i][0]=String.valueOf(m.getID());
			//���� ���
			data[i][1]=m.getName();
			//���� ��� ������
			data[i][2]=m.getClassname();
			
			
		}	
		//� ���������� �������
		table.setContent(data, columnNames);
	    pntop.removeAll();
	    pntop.add(scrollPane);
	    pntop.add(navpanel);
	      
	}
	Store(){
		
		setLayout(null);
		//��������� ������� � ������ ������
		pntop=new JPanel();
		pntop.setLayout(null);
		pnbottom=new JPanel();
		pnbottom.setLayout(null);
		
		
      table2 =new JMultiTableTwo(new String[][]{},columnNames2);
		//������ ������� �� ������ ����������� 
      final JTable ltable2=new JTable(table2); 
		 //������� ������ ������� ������
	      ltable2.getTableHeader().setReorderingAllowed(false);
	     //���������
	     ltable2.addMouseListener( new MouseAdapter()
		 	{
		 		public void mousePressed(MouseEvent event) {
		 			//�������� ��������
		 			int r=ltable2.getSelectedRow();
		 			selectParametr(r);
		 			
		 		}
		 	});
     
      
      //������� ������ � �������� �� ����������(�������)
		scrollPane2 = new JScrollPane(ltable2);
		
		
		lbfunction=new JLabel("F");
		lbfunction.setBounds(5, 0, 30, 30);
		lbfunction.setFont(new Font("Dialog", Font.ITALIC, 24));
		tffunction=new JTextField("");
		
		//��������� ������ ���������
		btedit=new JButton("���������");
	    btedit.setBounds(pntop.getWidth()-100, 30, 100, 30);
	    btedit.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//������ ������ ���������
	 			setParametrs();
	 		}
	 	});
	    //��������� ������ ������� ��������
	    btparamdel=new JButton("������� ��������");
	    btparamdel.setBounds(150, 30, 150, 30);
	    btparamdel.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//������ ������ ������� ��������
	 			deleteParametr(selectmaterial,selectparametr-4);
	 		}
	 	});
	    //��������� ������ �������� ��������
	    btparamedit=new JButton("������� ��������");
	    btparamedit.setBounds(0, 30, 150, 30);
	    btparamedit.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//������ ������ �������� ��������
	 			addParametr(selectmaterial);
	 			
	 		}
	 	}); 
	    
	    //�������� ������ ��������� ��� ����� ����� (��� ������ ���������)
	    navpanel2=new JPanel();
	    navpanel2.setLayout(null);
	    navpanel2.setBounds(pnbottom.getHeight()-30, 0, 30, pnbottom.getWidth());
	   
	    navpanel2.add(lbfunction);
	    navpanel2.add(tffunction);
	    tffunction.setBounds(30, 0, navpanel2.getWidth()-30, 30);
	    navpanel2.add(btedit);
	    
	    navpanel2.add(btparamedit);
	    navpanel2.add(btparamdel);
	    
	    pnbottom.add(scrollPane2);
	    pnbottom.add(navpanel2);
	    
		
      
       
      
      table=new JMultiTable(new String[][] {},columnNames);
		 final JTable ltable=new JTable(table); 
	      ltable.getTableHeader().setReorderingAllowed(false);
	     
	     ltable.addMouseListener( new MouseAdapter()
		 	{
		 		public void mousePressed(MouseEvent event) {
		 			int r=ltable.getSelectedRow();
		 			//�������� ��������
		 			selectMaterial(r);
		 			
		 		}
		 	});
	      
	      
	  scrollPane = new JScrollPane();
	  scrollPane.setViewportView(ltable);
	      
      scrollPane = new JScrollPane(ltable);	
      
      navpanel=new JPanel();
      navpanel.setLayout(null);
      navpanel.setBounds(pntop.getHeight()-30, 0, 30, pntop.getWidth());
      scrollPane2.setBounds(0, 0, pnbottom.getWidth(), pnbottom.getHeight());
      
      //�������� ������ �������� � �����
      btsmeta=new JButton("�����");
      btsmeta.setBounds(0, 0, 100, 30);
      
      btsmeta.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//������ �� ������ �����, ������� � �����
	 			actions(TO_SMETA);
	 		}
	 	});
      //�������� ������ ��������
      btadd=new JButton("��������");
      btadd.setBounds(100, 0, 100, 30);
      btadd.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//������ �� ������ ��������, ��������� �������� � �����
	 			actions(ADD_TO_SMETA);
	 		}
	 	});
      btcreate=new JButton("�������");
      btcreate.setBounds(200, 0, 100, 30);
      btcreate.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			
	 			addMaterial();
	 		}
	 	});
      btdelete=new JButton("�������");
      btdelete.setBounds(pntop.getWidth()-100, 0, 100, 30);
     btdelete.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			
	 			deleteMaterial(selectmaterial);
	 			
	 			
	 		}
	 	});
      navpanel.add(btsmeta);
      navpanel.add(btadd);
      navpanel.add(btcreate);
      navpanel.add(btdelete);
      
      pntop.add(scrollPane);
      pntop.add(navpanel);
      
      pntop.addComponentListener(new ComponentListener() {
			
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				reswin();
			}

			
			public void componentResized(ComponentEvent arg0) {
				reswin();
			}

			
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				reswin();
			}
		      });
		
		
		addComponentListener(new ComponentListener() {
			
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}

			
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub      
				reswin();
				
			}

			
			public void componentShown(ComponentEvent arg0) {
				reswin();			
			}
		});
		
		
		
		
		
		//����������� �� ���������, �� ���� �������������� ����� ����������
		content = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pntop, pnbottom);
		pnbottom.setMinimumSize(new Dimension(200,200));
		//������ �������������� ������
		content.setDividerSize(8);
		//�� �������
		content.setDividerLocation(600);
		//������������ ����������� ��� ������������� ������������
		content.setContinuousLayout(true);
		
		
		add(content);
		//���� �������
		createdemo();
	}
	//���������� � ����������� ���������
	void actions(int act){
		//act ����� ��������
	}
	
	 
}
