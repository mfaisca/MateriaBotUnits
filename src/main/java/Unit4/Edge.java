package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Edge")
public class Edge extends Unit{
	public Edge() {
		super("Edge");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6917};
				break;
			case HP:
				ids = new int[]{6918};
				break;
			case S1:
				ids = new int[]{6773};
				break;
			case S2:
				ids = new int[]{6775};
				break;
			case EX:
				ids = new int[]{6919};
				break;
			case AA:
				ids = new int[]{6801};
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
			case 6919:
				ret.removeHitDataById(7502);
				ret.fixMissingAuraAilment(1254, 448, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1255, 421, null, Ailment.Target.Party);
				ret.getAilmentById(1255).removeEffect(85);
		}
		return ret;
	}
}