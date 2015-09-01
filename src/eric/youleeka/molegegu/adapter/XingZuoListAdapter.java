package eric.youleeka.molegegu.adapter;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import eric.youleeka.molegegu.R;

public class XingZuoListAdapter extends BaseAdapter implements OnItemClickListener{
	private Context mContext;
	private List<String> oranNames;

	public XingZuoListAdapter(Context context) {
		this.mContext = context;
	}

	public void setList(List<String> items) {
		this.oranNames = items;
	}

	@Override
	public int getCount() {
		if (oranNames == null) {
			return 0;
		}
		return oranNames.size();
	}

	@Override
	public Object getItem(int position) {
		if (oranNames == null) {
			return null;
		}
		return oranNames.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (observer != null)
			super.unregisterDataSetObserver(observer);
	}

	class Holder {
		TextView tv_title;
		CheckBox checkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (oranNames == null || oranNames.isEmpty()
				|| oranNames.size() <= position)
			return null;

		String item = (String) oranNames.get(position);

		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.list_item_selectlevel, null);

			holder = new Holder();
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.organ_text);
			holder.checkBox = (CheckBox) convertView
					.findViewById(R.id.checkbox_level);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.checkBox.setClickable(false);
		holder.checkBox.setTag(position);
		
		holder.tv_title.setText(item);
		validateCheckItem(holder.checkBox, position, parent);

		return convertView;
	}

	private void validateCheckItem(CheckBox cBox, int index,
			final ViewGroup parent) {
		if (parent == null)
			return;
		if (!(parent instanceof ListView))
			return;

		ListView listView = (ListView) parent;

		boolean exist = false;
		SparseBooleanArray idsArray = listView.getCheckedItemPositions();
		if (idsArray.size() > 0) {
			for (int i = 0; i < idsArray.size(); i++) {
				int key = idsArray.keyAt(i);
				if (key == index) {
					boolean checked = idsArray.valueAt(i);
					if (checked)
						exist = true;
					else
						exist = false;
					break;
				}
			}
		}

		if (exist)
			cBox.setChecked(true);
		else
			cBox.setChecked(false);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (view == null)
			return;
		// exception
		if (!(parent instanceof ListView))
			return;
		
		ListView listView = (ListView) parent;
		
		CheckBox cBox = (CheckBox) view.findViewById(R.id.checkbox_level);

		boolean exist = false;
		SparseBooleanArray idsArray = listView.getCheckedItemPositions();
		if (idsArray.size() > 0) {
			for (int i = 0; i < idsArray.size(); i++) {
				int key = idsArray.keyAt(i);
				if (key == position) {
					boolean checked = idsArray.valueAt(i);
					if (checked)
						exist = true;
					else
						exist = false;
					break;
				}
			}
		}

		if (exist)
			cBox.setChecked(true);
		else
			cBox.setChecked(false);

	}
}