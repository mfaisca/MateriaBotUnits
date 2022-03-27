package UnitT0;
import com.materiabot.GameElements.*;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Rem")
public class Rem extends Unit{
	public Rem() { super("Rem"); }
	
	@Override
	public Ailment getSpecificAilment(Integer id) {
		Ailment ail = super.getSpecificAilment(id);
		if(ail.getId() == 641) //Siphon
			ail.setRank(0);
		if(ail.getId() == 642) //Siphon
			ail.setRank(0);
		return ail;
	}
	
	@Override
	public void loadFixGL() {
		this.getAbilities().values().stream()
			.flatMap(a -> a.getAilments().stream())
			.filter(a -> a.getId() == 641 || a.getId() == 642)
			.forEach(a -> a.setRank(0));
	}
}