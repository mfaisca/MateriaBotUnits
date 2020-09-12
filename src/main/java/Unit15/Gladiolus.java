package Unit15;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Gladiolus")
public class Gladiolus extends Unit{
	public Gladiolus() {
		super("Gladiolus", "gladio", "daddio");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10116};
				break;
			case HP:
				ids = new int[]{10117};
				break;
			case S1:
				ids = new int[]{9478};
				break;
			case S2:
				ids = new int[]{9482};
				break;
			case EX:
				ids = new int[]{9486};
				break;
			case AA:
				ids = new int[]{9493};
				break;
			case LD:
				ids = new int[]{9488, 10113};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 9478: //S1
				ret.getAilmentById(1768).setRank(-1);
				break;
			case 9486: //EX
				ret.getAilmentById(1772).setRank(-1);
				ret.fixMissingAuraAilment(1772, 770, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1772, 771, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1772, 772, Ailment.EffectType.E7, Ailment.Target.Party);
				break;
			case 9488: //LD
				ret.getAilmentById(1771).getEffects().get(0).rankData = ret.getAilmentById(1771).getEffects().get(1).rankData;
				ret.removeAilmentById(169);
				ret.getAilmentById(1771).getEffects().get(5).val_specify = 10113;
				break;
			case 9493: //AA
				ret.fixMissingAuraAilment(1903, 866, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}