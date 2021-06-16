package Unit5;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Gilgamesh")
public class Gilgamesh extends Unit{
	public Gilgamesh() {
		super("Gilgamesh", "gilg", "greg");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4521};
				break;
			case HP:
				ids = new int[]{4523};
				break;
			case S1:
				ids = new int[]{4517};
				break;
			case S2:
				ids = new int[]{4520};
				break;
			case EX:
				ids = new int[]{7469};
				break;
			case AA:
				ids = new int[]{4580};
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
			case 4521:
				ret.removeHitDataById(2858);
				break;
			case 4523:
				ret.removeHitDataById(2859);
				break;
			case 4520:
				ret.removeHitDataById(2858);
				ret.removeHitDataById(2859);
			case 7469:
				ret.getAilmentById(992).setRank(-1);
				ret.fixMissingAuraAilment(992, 203, Ailment.EffectType.E1, Ailment.Target.AoE);
				break;
		}
		return ret;
	}
}