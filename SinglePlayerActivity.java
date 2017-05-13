package com.example.admin.velha;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.WindowManager;

public class SinglePlayerActivity extends AppCompatActivity {
    Jogo J;
    int[][] TabuleiroJogo = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    boolean JogadorX = true;
    String mensagem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        J = new Jogo(this);
        setContentView(J);

    }

    class Jogo extends View {
        Jogo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas c) {
            super.onDraw(c);

            Display display = getWindowManager().getDefaultDisplay();
            Point tamanho = new Point();
            display.getSize(tamanho);
            int width = tamanho.x;
            int height = tamanho.y;

            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(getResources().getColor(R.color.branco));

            c.drawLine(width / 3, 0, width / 3, height, p);
            c.drawLine((width / 3) * 2, 0, (width / 3) * 2, height, p);

            c.drawLine(0, height / 3, width, height / 3, p);
            c.drawLine(0, (height / 3) * 2, width, (height / 3) * 2, p);

            p.setColor(getResources().getColor(R.color.corY));
            for (int n = 0; n < 3; n++) {
                for (int m = 0; m < 3; m++) {
                    if (TabuleiroJogo[m][n] == 2)
                        c.drawCircle(((width / 3) / 2) + (m * (width / 3)), ((height / 3) / 2) + (n * (height / 3)), ((width / 3) / 2), p);
                }
            }

            p.setColor(getResources().getColor(R.color.corX));
            for (int n = 0; n < 3; n++) {
                for (int m = 0; m < 3; m++) {
                    if (TabuleiroJogo[m][n] == 1) {
                        c.drawLine(m * (width / 3), n * (height / 3), (m + 1) * (width / 3), (n + 1) * (height / 3), p);
                        c.drawLine(m * (width / 3), (n + 1) * (height / 3), (m + 1) * (width / 3), n * (height / 3), p);
                    }

                }
            }

            p.setColor(getResources().getColor(R.color.Risco));
            p.setStrokeWidth(4f);


            if (TabuleiroJogo[0][0] != 0) {
                if (TabuleiroJogo[0][0] == TabuleiroJogo[1][0] && TabuleiroJogo[0][0] == TabuleiroJogo[2][0]) {
                    c.drawLine(0, ((height / 3) / 2), width, ((height / 3) / 2), p);
                }
            }
            if (TabuleiroJogo[0][1] != 0) {
                if (TabuleiroJogo[0][1] == TabuleiroJogo[1][1] && TabuleiroJogo[0][1] == TabuleiroJogo[2][1]) {
                    c.drawLine(0, ((height / 3) / 2) + (height / 3), width, ((height / 3) / 2) + (height / 3), p);
                }
            }
            if (TabuleiroJogo[0][2] != 0) {
                if (TabuleiroJogo[0][2] == TabuleiroJogo[1][2] && TabuleiroJogo[0][2] == TabuleiroJogo[2][2]) {
                    c.drawLine(0, ((height / 3) / 2) + ((height / 3) * 2), width, ((height / 3) / 2) + ((height / 3) * 2), p);
                }
            }


            if (TabuleiroJogo[0][0] != 0) {
                if (TabuleiroJogo[0][0] == TabuleiroJogo[0][1] && TabuleiroJogo[0][0] == TabuleiroJogo[0][2]) {
                    c.drawLine((width / 3) / 2, 0, (width / 3) / 2, height, p);
                }
            }
            if (TabuleiroJogo[1][0] != 0) {
                if (TabuleiroJogo[1][0] == TabuleiroJogo[1][1] && TabuleiroJogo[1][0] == TabuleiroJogo[1][2]) {
                    c.drawLine(((width / 3) / 2) + (width / 3), 0, ((width / 3) / 2) + (width / 3), height, p);
                }
            }
            if (TabuleiroJogo[2][0] != 0) {
                if (TabuleiroJogo[2][0] == TabuleiroJogo[2][1] && TabuleiroJogo[2][0] == TabuleiroJogo[2][2]) {
                    c.drawLine(((width / 3) / 2) + ((width / 3) * 2), 0, ((width / 3) / 2) + ((width / 3) * 2), height, p);
                }
            }

            if (TabuleiroJogo[0][0] != 0) {
                if (TabuleiroJogo[0][0] == TabuleiroJogo[1][1] && TabuleiroJogo[0][0] == TabuleiroJogo[2][2]) {
                    c.drawLine(0, 0, width, height, p);
                }
            }
            if (TabuleiroJogo[2][0] != 0) {
                if (TabuleiroJogo[2][0] == TabuleiroJogo[1][1] && TabuleiroJogo[2][0] == TabuleiroJogo[0][2]) {
                    c.drawLine(0, height, width, 0, p);
                }
            }


            for(int i=0,m=0;m<3;m++){
                for(int n=0;n<3;n++){
                    if(TabuleiroJogo[m][n] != 0)
                        i++;
                }
                if(i == 9)
                    fimDeJogo(1);
            }
        }

        public boolean onTouchEvent(MotionEvent jogador) {
            if (jogador.getAction() == MotionEvent.ACTION_UP) {
                float x = jogador.getX();
                float y = jogador.getY();

                Display display = getWindowManager().getDefaultDisplay();
                Point tamanho = new Point();
                display.getSize(tamanho);
                int width = tamanho.x;
                int height = tamanho.y;

                for (int n = 0; n < 3; n++) {
                    for (int m = 0; m < 3; m++) {
                        if (x > m * (width / 3) && x < (m + 1) * (width / 3) && y > n * (height / 3) && y > (n + 1) * (height / 3)) {
                            if (TabuleiroJogo[m][n] == 0) {
                                // Operador ternário, igual a: if(vezdox)tabuleiro[m][n] = 1; else tabuleiro[m][n] = 2;
                                TabuleiroJogo[m][n] = (JogadorX ? 1 : 2);
                                JogadorX = !JogadorX;
                            }
                        }
                    }
                }
                J.invalidate();
            }
            return true;
        }
    }
    public void fimDeJogo(int i) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Fim de Jogo!");

        if(i == 0){
            if(JogadorX)
                mensagem = "O Ganhou!";
            else
                mensagem = "X Ganhou!";
        }
        else
            mensagem = "Velha!";

        alertDialogBuilder.setMessage(mensagem + " Clique em OK para jogar novamente").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
