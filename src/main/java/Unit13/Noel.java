package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Noel")
public class Noel extends Unit{
	public Noel() {
		super("Noel");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7780};
				break;
			case HP:
				ids = new int[]{4672, 4779};
				break;
			case S1:
				ids = new int[]{4663, 4664};
				break;
			case S2:
				ids = new int[]{4668, 4778};
				break;
			case EX:
				ids = new int[]{7778, 7779};
				break;
			case AA:
				ids = new int[]{4680};
				break;
			case LD:
				ids = new int[]{};
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
			case 4778: //S2+
				ret.getAilmentById(520).setRank(-1);
			case 4779: //HP+
			case 4664: //S1+
				ret.addStaticHit("Enabled when attacking a broken target", 0);
				break;
			case 7779: //EX+
				ret.addStaticHit("Enabled when attacking a broken target", 0);
			case 7778: //EX
				ret.fixMissingAuraAilment(1484, 563, Ailment.EffectType.E1, Ailment.Target.Party).rankData = new String[] {"20", "20", "20"};
				ret.fixMissingAuraAilment(1484, 564, Ailment.EffectType.E5, Ailment.Target.Party).rankData = new String[] {"20", "20", "20"};
				ret.getDetails().getAilments().get(0).getAuras().remove(565);
				ret.getDetails().getAilments().get(0).getAuras().remove(566);
				break;
		}
		return ret;
	}
}