package com.example.tictacktoe;

import static com.example.tictacktoe.R.id.restartBtn;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    GridView coursesGV;
    AppCompatButton restartBt;
    int chance =0;
    public int[][] mat = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        coursesGV = findViewById(R.id.idGVcourses);
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();

        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        courseModelArrayList.add(new CourseModel(R.color.white, R.drawable.blank_image));
        TextView result = findViewById(R.id.resultText);
        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
        restartBt=findViewById(R.id.restartBtn);
        restartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chance = 0;
                for (int[] row : mat) {
                    Arrays.fill(row, -1);
                }
                for (CourseModel courseModel : courseModelArrayList) {
                    courseModel.setImageResId(R.drawable.blank_image);
                }
                adapter.notifyDataSetChanged();
                result.setVisibility(View.GONE);
                coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int row = position / 3;
                        int col = position % 3;
                        CourseModel clickedItem = courseModelArrayList.get(position);

                        if (mat[row][col] == -1) {
                            if (chance % 2 == 0) {
                                clickedItem.setImageResId(R.drawable.x);
                                mat[row][col] = 1;
                            } else {
                                clickedItem.setImageResId(R.drawable.zero);
                                mat[row][col] = 0;
                            }

                            adapter.notifyDataSetChanged();

                            if (checkWin()) {
                                result.setText((chance % 2 == 0 ? "X" : "O") + " wins");
                                result.setVisibility(View.VISIBLE);
                                coursesGV.setOnItemClickListener(null); // Disable further clicks
                            } else if (chance == 8) {
                                result.setText("Draw");
                                result.setVisibility(View.VISIBLE);
                            }

                            chance++;
                        }
                    }
                });
            }
        });
        coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int row = position / 3;
                int col = position % 3;
                CourseModel clickedItem = courseModelArrayList.get(position);

                if (mat[row][col] == -1) {
                    if (chance % 2 == 0) {
                        clickedItem.setImageResId(R.drawable.x);
                        mat[row][col] = 1;
                    } else {
                        clickedItem.setImageResId(R.drawable.zero);
                        mat[row][col] = 0;
                    }

                    adapter.notifyDataSetChanged();

                    if (checkWin()) {
                        result.setText((chance % 2 == 0 ? "X" : "O") + " wins");
                        result.setVisibility(View.VISIBLE);
                        coursesGV.setOnItemClickListener(null); // Disable further clicks
                    } else if (chance == 8) {
                        result.setText("Draw");
                        result.setVisibility(View.VISIBLE);
                    }

                    chance++;
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((mat[i][0] != -1 && mat[i][0] == mat[i][1] && mat[i][1] == mat[i][2]) ||
                    (mat[0][i] != -1 && mat[0][i] == mat[1][i] && mat[1][i] == mat[2][i])) {
                return true;
            }
        }

        if ((mat[0][0] != -1 && mat[0][0] == mat[1][1] && mat[1][1] == mat[2][2]) ||
                (mat[0][2] != -1 && mat[0][2] == mat[1][1] && mat[1][1] == mat[2][0])) {
            return true;
        }

        return false;
    }
}