package com.realpower.petitionwatch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.realpower.petitionwatch.R;
import com.realpower.petitionwatch.adapter.ContactAdapter;
import com.realpower.petitionwatch.bean.ContactBean;
import com.realpower.petitionwatch.net.MyCallback;
import com.realpower.petitionwatch.net.result.ContactResult;
import com.realpower.petitionwatch.util.ChineseToPinyinHelper;
import com.realpower.petitionwatch.view.LetterIndexView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;

@EActivity
public class ContactsActivity extends BaseActivity {
    private ContactAdapter adapter;
    @ViewById
    ListView lv_contact;
    private List<ContactBean> data;
    @ViewById
    TextView show_letter_in_center;
    @ViewById
    LetterIndexView letter_index_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }


    @AfterViews
    void initViews() {
        setTitleName("通讯录");
        data = new ArrayList<>();
        adapter = new ContactAdapter(this, data);
        lv_contact.setAdapter(adapter);
        letter_index_view.setTextViewDialog(show_letter_in_center);
        letter_index_view.setUpdateListView(new LetterIndexView.UpdateListView() {
            @Override
            public void updateListView(String currentChar) {
                int positionForSection = adapter.getPositionForSection(currentChar.charAt(0));
                lv_contact.setSelection(positionForSection);
            }
        });
        lv_contact.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int sectionForPosition = adapter.getSectionForPosition(firstVisibleItem);
                letter_index_view.updateLetterIndexView(sectionForPosition);
            }
        });
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactDetailActivity_.intent(ContactsActivity.this).bean(data.get(i)).start();
            }
        });

        getContact();
    }

    private void getContact() {
        Call<ContactResult> call = apiService.allContacts();
        call.enqueue(new MyCallback<ContactResult>() {
            @Override
            public void onSuccessRequest(ContactResult result) {
                if ("1".equals(result.getStatus())) {
                    setData(result.getMessage());
                }
            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<ContactResult> call, Throwable t) {

            }
        });
    }

    private void setData(List<ContactBean> message) {
        data.clear();
        data.addAll(message);
        for (ContactBean bean : data) {
            String convert = ChineseToPinyinHelper.getInstance().getPinyin(bean.getSysUser().getNickname()).toUpperCase();
            //  bean.setPinyin(convert);
            String substring = convert.substring(0, 1);
            if (substring.matches("[A-Z]")) {
                bean.setFirstLetter(substring);
            } else {
                bean.setFirstLetter("#");
            }

        }
        Collections.sort(data, new Comparator<ContactBean>() {
            @Override
            public int compare(ContactBean lhs, ContactBean rhs) {
                if (lhs.getFirstLetter().contains("#")) {
                    return 1;
                } else if (rhs.getFirstLetter().contains("#")) {
                    return -1;
                } else {
                    return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
                }
            }
        });
        adapter.notifyDataSetChanged();
    }
}
