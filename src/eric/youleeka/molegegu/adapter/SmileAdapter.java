package eric.youleeka.molegegu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import eric.youleeka.molegegu.R;
import eric.youleeka.molegegu.mode.Content;

public class SmileAdapter extends BaseAdapter {
	
	private List<Content> list;
	private Context context;
	public  SmileAdapter(Context context,List<Content> list){
		this.context = context;
		this.list = list;
	}
	
	public List<Content> setList(){
		return list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(list==null){
			return null;
		}
		
		if (convertView == null) {
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
		}
		TextView contentInfo = (TextView)convertView.findViewById(R.id.contentInfo);
		contentInfo.setText(list.get(position).getTitle());
		
		TextView online_news_subtitle = (TextView)convertView.findViewById(R.id.online_news_subtitle);
		online_news_subtitle.setText(list.get(position).getDes());
		
		return convertView;
	}

}
