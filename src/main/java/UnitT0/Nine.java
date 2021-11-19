package UnitT0;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Nine")
public class Nine extends Unit{
	public Nine() { super("Nine"); }
	
	@Override
	public Ailment getSpecificAilment(Integer id) {
		Ailment ail = super.getSpecificAilment(id);
		if(ail.getId() == 1459) { //Wild Roar
			String desc = ail.generateDescription();
			desc = "" + desc;
			ail.setFakeDesc(new Text(desc));
			ail.setDuration(8);
			ail.setArgs(new Integer[] {1,0});
		}
		return ail;
	}
	
	@Override
	public void loadFixGL() {
		this.getAbilities().values().stream()
			.flatMap(a -> a.getAilments().stream())
			.filter(a -> a.getId() == 1459 && a.getAilmentConditionId() == 13)
			.forEach(a -> a.setArgs(new Integer[] {1,0}));
	}
	@Override
	public void loadFixJP() {
		super.loadFixGL();
	}
}