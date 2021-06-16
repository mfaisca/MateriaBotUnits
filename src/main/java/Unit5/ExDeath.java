package Unit5;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.ExDeath")
public class ExDeath extends Unit{
	public ExDeath() {
		super("ExDeath", "exd", "ex death", "xdeath");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7460};
				break;
			case HP:
				ids = new int[]{7461};
				break;
			case S1:
				ids = new int[]{6892};
				break;
			case S2:
				ids = new int[]{6896};
				break;
			case EX:
				ids = new int[]{7459, 7733};
				break;
			case AA:
				ids = new int[]{7465};
				break;
			case LD:
				ids = new int[]{10755};
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
			case 6896:
				ret.getAilmentById(1354).removeEffect(85);
				Ailment.EffectGrouping eg1 = ret.getAilmentById(1354).getEffects().stream().filter(ee -> ee.effectId == 3).findFirst().get();
				eg1.rankData = new String[] {"010010010", "010010010", "010010010"};
				break;
			case 7459:
				ret.addStaticHit("**𝓣𝓱𝓮 𝓵𝓪𝔀𝓼 𝓸𝓯 𝓽𝓱𝓮 𝓾𝓷𝓲𝓿𝓮𝓻𝓼𝓮 𝓶𝓮𝓪𝓷 𝓷𝓸𝓽𝓱𝓲𝓷𝓰!**", 0);
				Ailment.EffectGrouping eg = ret.getAilmentById(1355).getEffects().stream().filter(ee -> ee.effectId == 194).findFirst().get();
				eg.val_specify = 7733;
				break;
			case 7733:
				
				break;
		}
		return ret;
	}
}