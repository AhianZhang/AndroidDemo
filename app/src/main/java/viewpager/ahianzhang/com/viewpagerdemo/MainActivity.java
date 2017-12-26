package viewpager.ahianzhang.com.viewpagerdemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
private  static  final int[]picIds = {
        R.drawable.pic1,
        R.drawable.pic2,
        R.drawable.pic3,
};
    private Button preBtn = null;
    private Button jumpBtn = null;
    private Button nextBtn = null;
    private EditText editText = null;
    private ViewPager viewPager = null;
    private  int currentIdx = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preBtn   = (Button) findViewById(R.id.act_main_pre_btn);
        jumpBtn  = (Button) findViewById(R.id.act_main_jump_btn);
        nextBtn  = (Button) findViewById(R.id.act_main_next_btn);
        editText = (EditText) findViewById(R.id.act_main_idx_et);
        viewPager= (ViewPager) findViewById(R.id.act_view_pager);

        preBtn.setOnClickListener(onBtnClickListener);
        jumpBtn.setOnClickListener(onBtnClickListener);
        nextBtn.setOnClickListener(onBtnClickListener);
        viewPager.setAdapter(new PicPagerAdapter());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
currentIdx = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private View.OnClickListener onBtnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == preBtn){
                int idx = currentIdx -1;
                if (idx>=0){
                    viewPager.setCurrentItem(idx);
                }}
                else if (view == nextBtn){
                int idx = currentIdx +1;
                if (idx < picIds.length){
                    viewPager.setCurrentItem(idx);
                }
            }
            else if (view == jumpBtn){
                String idxStr = editText.getText().toString();
                if (!idxStr.isEmpty()){

                    int idx = Integer.parseInt(editText.getText().toString())-1;
                   if (idx >= 0 && idx < picIds.length){
                       viewPager.setCurrentItem(idx);
                   }
                }
            }
            }

    };

    public class PicPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return picIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(picIds[position]);
            container.addView(imageView);
            return imageView;

        }
    }
}
