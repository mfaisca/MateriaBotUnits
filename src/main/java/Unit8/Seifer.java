package Unit8;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Seifer")
public class Seifer extends Unit{
	public Seifer() {
		super("Seifer");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7888};
				break;
			case HP:
				ids = new int[]{7889};
				break;
			case S1:
				ids = new int[]{7880};
				break;
			case S2:
				ids = new int[]{7882};
				break;
			case EX:
				ids = new int[]{7885};
				break;
			case AA:
				ids = new int[]{5049};
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
			case 5049:
				ret.getAilmentById(1061).setRank(2);
				break;
			case 7885:
				ret.getDetails().getAilments().get(0).getEffects().get(0).effectId = Ailment.EffectType.E61.getId();
				ret.getDetails().getAilments().get(0).getEffects().get(2).rankData[0] = "20002020";
		}
		return ret;
	}
}