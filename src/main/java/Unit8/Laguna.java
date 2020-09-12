package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Laguna")
public class Laguna extends Unit{
	public Laguna() {
		super("Laguna");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9804};
				break;
			case HP:
				ids = new int[]{9805};
				break;
			case S1:
				ids = new int[]{9883};
				break;
			case S2:
				ids = new int[]{9885};
				break;
			case EX:
				ids = new int[]{9803};
				break;
			case AA:
				ids = new int[]{4035};
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
			case 4035:
				ret.getAilmentById(934).getEffects().get(0).rankData[0] = ret.getAilmentById(934).getEffects().get(0).rankData[1];
				ret.fixMissingAuraAilment(934, 148, Ailment.EffectType.E5, Ailment.Target.Party);
		}
		return ret;
	}
}