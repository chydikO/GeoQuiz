package com.example.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionsBank = new Question[] {
      new Question(R.string.question_australia, true),
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa,false),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia,true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.questions_text_view);
        updateQuestion();

        mTrueBtn = (Button) findViewById(R.id.btnTrue);

        //назначается слушатель, информирующий о нажатии виджета Button с именем mTrueButton
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast toast = Toast.makeText(MainActivity.this,
                                                R.string.correct_toast,
                                                Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                */
                checkAnswer(true);
            }
        });

        mFalseBtn = (Button) findViewById(R.id.btnFalse);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast toast = Toast.makeText(MainActivity.this,
                                        R.string.incorrect_toast,
                                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                */
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) < 0 ? mQuestionsBank.length - 1 : --mCurrentIndex;
                updateQuestion();
            }
        });

    }

    private void updateQuestion() {
        int question = mQuestionsBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    /**
     * Метод получает логическую переменную, которая указывает, какую кнопку на-
     * жал пользователь:  TRUE или  FALSE . Ответ пользователя проверяется по ответу те-
     * кущего объекта  Question .после определения правильности ответа, ме-
     * тод создает уведомление для вывода соответствующего сообщения.
     */
    private void checkAnswer(boolean userPressTrue) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;
        if (userPressTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
