package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Bartz")
public class Bartz extends Unit{
	public Bartz() {
		super("Bartz", "butz", "buttz");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{3485};
				break;
			case HP:
				ids = new int[]{6175};
				break;
			case S1:
				ids = new int[]{6377, 6378};
				break;
			case S2:
				ids = new int[]{6381, 6382};
				break;
			case EX:
				ids = new int[]{6174};
				break;
			case AA:
				ids = new int[]{6143};
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
			case 6377:
			case 6378:
				ret.setMastery(6377, 6378, 1);
				break;
			case 6381:
			case 6382:
				ret.setMastery(6381, 6382, 1);
				break;
			case 6174:
				ret.fixMissingAuraAilment(1227, 370, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
			case 6143:
				ret.fixMissingAuraAilment(1219, 371, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1219, 372, Ailment.EffectType.E5, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}