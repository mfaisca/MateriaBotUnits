package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.Objects;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Snow")
public class Snow extends Unit{
	public Snow() { super("Snow"); }
	
//	@Override
//	public BestAbilities getAbility(AttackName type, Region region) {
//		BestAbilities ret = null;
//		switch(type) {
//			case LD:
//				ret = new BestAbilities(this, 14817, 14817, 14818, 14819);
//				break;
//			default:
//				break;
//		}
//		if(ret == null)
//			return super.getAbility(type, region);
//		if(ret.getAbilities().isEmpty())
//			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
//		return ret;
//	}

//	@Override
//	public Ability getSpecificAbility(Integer id) {
//		if(id == null) return null;
//		Ability r = getAbilities().get(id);
//		if(r == null) return null;
//		if(r.getId() == 14810)
//			for(Ailment a : r.getAilments())
//				if(a.getId() == 331)
//					a.setFakeDesc(new Text("-50% BRV Damage taken" + System.lineSeparator() + "-10% BRV Damage taken per BRV attack taken (up to -50%)"));
//		return Objects.requireNonNullElse(r, Ability.NULL(this, id));
//	}
	
	@Override
	public Ability getSpecificAbility(Integer id) {
		if(id == null) return null;
		Ability ab = Objects.requireNonNullElse(getAbilities().get(id), Ability.NULL(this, id));
		for(Ailment ail : ab.getAilments())
			if(ail.getId() == 331)
				ail.setFakeDesc(new Text("-50% BRV Damage taken" + System.lineSeparator() + "-10% BRV Damage taken per BRV attack taken (up to -50%)"));
		return ab;
	}

	@Override
	public Ailment getSpecificAilment(Integer id) {
		Ailment ail = super.getSpecificAilment(id);
		if(ail.getId() == 331)
			ail.setFakeDesc(new Text("-50% BRV Damage taken" + System.lineSeparator() + "-10% BRV Damage taken per BRV attack taken (up to -50%)"));
		return ail;
	}
}