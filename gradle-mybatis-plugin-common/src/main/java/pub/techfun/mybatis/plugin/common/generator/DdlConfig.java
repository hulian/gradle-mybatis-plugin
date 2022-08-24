package pub.techfun.mybatis.plugin.common.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DdlConfig {
    private Boolean enabled = false;
    private Boolean overwrite = false;
}

