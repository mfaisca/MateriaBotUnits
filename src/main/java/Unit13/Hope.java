package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Hope")
public class Hope extends Unit{
	public Hope() {
		super("Hope");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7544};
				break;
			case HP:
				ids = new int[]{7545};
				break;
			case S1:
				ids = new int[]{7534};
				break;
			case S2:
				ids = new int[]{7535};
				break;
			case EX:
				ids = new int[]{7539};
				break;
			case AA:
				ids = new int[]{7564};
				break;
			case LD:
				ids = new int[]{11115};
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
			case 7564:
				Ailment.Aura hpDmgRed = ret.fixMissingAuraAilment(1448, 538, null, Ailment.Target.Party);
				hpDmgRed.rankData[3] = hpDmgRed.rankData[3].replace("-", "");
				ret.fixMissingAuraAilment(1448, 539, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
			case 11115:
				ret.getAilmentById(2124).getEffects().get(0).rankData = new String[] {"1020"};
				ret.getAilmentById(2124).getEffects().get(1).rankData = new String[] {"1020"};
				break;
		}
		return ret;
	}
}