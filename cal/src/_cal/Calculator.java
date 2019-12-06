package _cal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener { // JFrame과  ActionListener 상속
	JTextField t1;
	JButton[] button;
	JButton AC;
	JPanel p1;
	JPanel p2;
	JPanel p11;
	JPanel p21;
	String[] btn= {"1","2","3","+","4","5","6","-","7","8","9"
	       ,"*","0",".","=","/"};
	float[] num=new float[20];  // 계산할 수들의 배열
	String value=""; 
	String result;
	String printvalue="";
	String[] opersave=new String [20]; //복수행 연산을 위한 배열
	int count=0;
	
	
	public Calculator() {  // GUI
		setTitle("계산기");

		p1 = new JPanel();
		p2 = new JPanel();
		p11 = new JPanel();
		p21 = new JPanel(new GridLayout(4, 4, 10, 10));
		t1=new JTextField("", 25);
		AC = new JButton("AC");
		button = new JButton[16];
		AC.addActionListener(this);
		
		for(int i=0;i<16;i++)
		{
			button[i]= new JButton(btn[i]);
			p21.add(button[i]);
			button[i].addActionListener(this);	
		}		
		add("North", p1);
		add("Center", p2);
		p1.add(t1);
		p1.add(p11);
		p2.add(p21);
		p11.add(AC);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setVisible(true);
		}			
		public void actionPerformed(ActionEvent e) {  //event 처리

			String input = e.getActionCommand();
			if(input.equals("AC")) //AC
			{
				for(int i=0;i<count;i++) {
					num[i]=0;
					opersave[i]="";
				}
				count=0;
				value="";
				result="";
				printvalue="";
				t1.setText("계산기! ( 7를 7번 누르고 enter ^-^)");
			}
			else if(input.equals(btn[3])) 
			{ //+
				if(value=="") { // 연산자 두번 이상 누를 시 오류
					t1.setText("숫자를 입력하세요");
				}
				else
				{
				num[count]=Float.parseFloat(value);
				opersave[count] =input;
				printvalue+=input;
				value="";
				count++;
				t1.setText(printvalue);
				System.out.println(printvalue);
				}
			}
			else if(input.equals(btn[7])) 
			{ //-
				if(value=="") { // 연산자 두번 이상 누를 시 오류
					t1.setText("숫자를 입력하세요");
				}
				else
				{
					num[count]=Float.parseFloat(value);
					opersave[count] =input;
					value="";
					count++;
					printvalue+=input;
					t1.setText(printvalue);
					System.out.println(printvalue);
				}
			}
			else if(input.equals(btn[11])) 
			{//*
				if(value=="") { // 연산자 두번 이상 누를 시 오류
					t1.setText("숫자를 입력하세요");
				}
				else
				{
					num[count]=Float.parseFloat(value);  //수배열에 대입
					opersave[count] =input; //연산 배열에 대입
					value="";  //값 초기화
					count++;  // 배열 인덱스 증기
					printvalue+=input;  
					t1.setText(printvalue);
					System.out.println(printvalue);
				}
			}
			else if (input.equals(btn[15]))
			 {//  /
				if(value=="") { // 연산자 두번 이상 누를 시 오류
					t1.setText("숫자를 입력하세요");
				}
				else
				{
					num[count]=Float.parseFloat(value); //수배열에 대입
					opersave[count] =input;  //연산 배열에 대입
					value=""; //값 초기화
					count++;  // 배열 인덱스 증기
					printvalue+=input;
					t1.setText(printvalue);
					System.out.println(printvalue);
				}
			}
			else if(input.equals(btn[14])) 
			{// =
				if(value=="") { // 연산자 두번 이상 누를 시 오류
					t1.setText("숫자를 입력하세요");
				}
				else if(value.equals("7777777"))  // 이스터 에그
				{
					t1.setText("행운이 가득하길!!");
					System.out.println("행운이 가득하길!!");
				}
				else  // 우선순위
				{
				num[count]=Float.parseFloat(value); // 수배열에 수 대입
				int i=0;
					while(i<count) 
					{
						if (opersave[i].equals("*"))  //* 발견시
						{
							num[i] = num[i] * num[i+1]; // 곱
							for(int j=i+1;j<count;j++)  // 연산배열, 수 배열 인덱스 한 칸씩 당기고 
							{
								 num[j]=num[j+1];
								 opersave[j-1]=opersave[j];
							 }
							 count--;            // 인덱스감소
						} 
					if (opersave[i].equals("/"))  //  '/' 발견시
					{
						num[i] = num[i] / num[i+1]; // 나누기
						for(int j=i+1;j<count;j++) // 연산배열, 수 배열 인덱스 한 칸씩 당기고 
						{
							num[j]=num[j+1];
							opersave[j-1]=opersave[j];
						}
						count--; // 인덱스감소

					}
					if(!opersave[i].equals("*")&&!opersave[i].equals("/")) { // 연산 배열 한 칸씩 당긴 후 연산[i]배열에 '*' 나 '/'검사 
																			 // 더 이상  '*' 나 '/' 가 없다면  i++
						i++;
					}
				}
				 // 곱셈, 나눗셈 우선순위 계산 완료
				for(i=0;i<count;i++) 
				{
					 if (opersave[i].equals("+")) // +이면
					 {
						 num[i+1]=num[i]+num[i+1]; // 합
					 }
					 if (opersave[i].equals("-"))// -이면
					 {
						 num[i+1]=num[i]-num[i+1];// 뺄셈
					 }
				}
				if(num[count]<0) { // 결과가 음수면
					num[count]=0;  // 0으로 표기
				}
				result=String.format("%.2f",num[count]); // 소수점 둘째 자리까지로 포맷	
				if(result.length()>12) // 계산 결과 10자리가(소수점제외) 초과 시 오류
				{
					t1.setText("10자리까지 가능!");					
				}
				else // 계산 결과 10자리까지 표기(소수점제외)
				{
					count=0;
					t1.setText(result);
					System.out.println(printvalue+" = "+result);

				}
				for(i=0;i<count;i++)   // 연산배열, 수 배열 초기화
				{
					num[i]=0;
					opersave[i]="";
				}
				value=""; //값 초기화
				result="";	//결과 초기화
				printvalue=""; 
			}
		}
		else if(input.equals(btn[13])) 
		{// .
			value+=input;
			if(value.equals(".")) { //시작시 소수점 누를 시 오류
				t1.setText("숫자를 입력하세요");
			}
			else 
			{
				printvalue+=input;
				t1.setText(printvalue);
				System.out.println(printvalue);
			}	
		}
		else  // 0-9
		{
			value+=input;
			printvalue+=input;
			t1.setText(printvalue);
			System.out.println(printvalue);
		}
	}
	public static void main(String[] args) { //메인
		// TODO Auto-generated method stub
		new Calculator();
	}
}
