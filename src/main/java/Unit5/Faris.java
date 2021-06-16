package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import com.materiabot.GameElements.Ailment.Target;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Faris")
public class Faris extends Unit{
	public Faris() {
		super("Faris");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5443};
				break;
			case HP:
				ids = new int[]{5242};
				break;
			case S1:
				ids = new int[]{5441, 5442};
				break;
			case S2:
				ids = new int[]{5446, 5447};
				break;
			case EX:
				ids = new int[]{5241, 5236};
				break;
			case AA:
				ids = new int[]{4363};
				break;
			case LD:
				ids = new int[]{12309};
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
			case 5441: //S1
			case 5442: //S1+
				ret.setMastery(5441, 5442, 1);
				ret.addStaticHit("+50% BRV Damage on debuffed targets", 0); //According to Safeena, no value in data
				break;
			case 5446: //S2
			case 5447: //S2+
				ret.setMastery(5446, 5447, 1);
				break;
			case 5241: //EX
				ret.fixMissingAuraAilment(1082, 272, null, Target.Party);
				ret.addStaticHit("+50% BRV Damage on debuffed targets", 0); //According to Safeena, no value in data
				break;
			case 5236: //EX Trigger
				ret.fixMissingAuraAilment(1082, 272, null, Target.Party);
				ret.removeHitDataById(6226);
				ret.getDetails().setMovementCost(30);
				break;
			case 12309:
				ret.getAilmentById(2390).getEffects().get(4).val_specify = -20;
				break;
		}
		return ret;
	}
}